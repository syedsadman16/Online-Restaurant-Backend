package com.cs322.ors.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cs322.ors.model.ChefJob;
import com.cs322.ors.model.DeliveryJobs;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.DishOrder;
import com.cs322.ors.model.Order;
import com.cs322.ors.model.Transaction;
import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.ChefJobService;
import com.cs322.ors.service.DeliveryJobsService;
import com.cs322.ors.service.DishService;
import com.cs322.ors.service.OrderService;
import com.cs322.ors.service.TransactionService;
import com.cs322.ors.service.UserService;
import com.cs322.ors.service.VipService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	public OrderService orderService;

	@Autowired
	public VipService vipService;

	@Autowired
	public TransactionService transactionService;

	@Autowired
	public UserService userService;

	@Autowired
	public DishService dishService;

	@Autowired
	public DeliveryJobsService deliveryJobsService;
	
	@Autowired
	public ChefJobService chefJobService;
	
	@GetMapping// Get all customer own orders or all orders
	@PreAuthorize("isAuthenticated()")
	public List<Order> getOrders(Authentication authUser) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		if (currentUser.getRole() == "MANAGER") {
			return orderService.getAllOrders();
		} else {
			return orderService.getOrderByUser(currentUser.getId());
		}

	}

	@GetMapping("/{orderId}") // Get customers order by id
	@PreAuthorize("isAuthenticated()")
	public Order getOrderWithId(@PathVariable long orderId, Authentication authUser) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		Optional<Order> order = orderService.getOrderById(orderId);
		if (order.isPresent()) {
			Order theOrder = order.get();
			boolean isTheirs = theOrder.getCustomer().getId() == currentUser.getId();
			boolean isManager = currentUser.getRole() == "MANAGER";
			if (isTheirs || isManager) {
				return theOrder;
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return null;
		}
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('CUSTOMER','VIP','MANAGER')")
	public void makeOrder(@Valid @RequestBody JsonNode rawOrder, Authentication authUser) throws Exception {

		// Get json paramaters
		int orderType = rawOrder.get("type").intValue();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Long>> rawDishOrder;
		try {
			rawDishOrder = mapper.readValue(rawOrder.get("dishes").toString(),
					new TypeReference<List<Map<String, Long>>>() {
					});
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Get user & their dishes for order
		List<DishOrder> dishOrders = rawDishOrder.stream()
				.map(obj -> new DishOrder(dishService.getDish(obj.get("id")).get(), obj.get("quantity").intValue()))
				.collect(Collectors.toList());
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		boolean isVIP = currentUser.getRole() == "VIP";
		boolean isCustomer = currentUser.getRole() == "CUSTOMER" || isVIP;
		boolean isDishesSpecial = dishOrders.stream().anyMatch(dishOrder -> dishOrder.getDish().isSpecial());
		boolean canOrderDishTypes = (isVIP && isDishesSpecial) || (isCustomer && !isDishesSpecial);

		// Check if customer can order.
		if (canOrderDishTypes) {
			// Get discounted sum of order
			BigDecimal originalAmount = dishOrders.stream().map(
					dishOrder -> dishOrder.getDish().getPrice().multiply(BigDecimal.valueOf(dishOrder.getQuantity())))
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			BigDecimal newAmount = vipService.applyDiscount(originalAmount, currentUser);
			BigDecimal currentBalance = transactionService.getTransactionSumByCustomer(currentUser);

			// Check funds. Create a transaction & jobs for the order if possible.
			if (currentBalance.compareTo(newAmount) == -1) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Account don't have enough funds");
			} else {			
				Order order = orderService.makeOrder(currentUser, dishOrders, orderType);
				transactionService.createTransaction(
						new Transaction(currentUser, newAmount, String.format("OrderId: %d", order.getId()), 0));
				//TODO: Make job for chef
				chefJobService.addChefJob(new ChefJob(dishOrders.get(0).getDish().getChef(), order));
				
				if(order.getType() == 1) {
					deliveryJobsService.addDeliveryJob(new DeliveryJobs(order));
				}

			}
		} else {
			
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can not order special dishes");

		}
	}
	
	@PostMapping("/{orderId}")
	@PreAuthorize("isAuthenticated()")
	public void setCompleted(@PathVariable long orderId) {
		orderService.setCompleted(orderId);
	}
	
	
	@PutMapping("/{orderId}") // Update a customers order
	@PreAuthorize("isAuthenticated()")
	public void updateOrder(@Valid @RequestBody Order order, @PathVariable long orderId, Authentication authUser) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		Optional<Order> theOrder = orderService.getOrderById(orderId);
		if (theOrder.isPresent()) {
			Order theOrder2 = theOrder.get();
			boolean isTheirs = theOrder2.getCustomer().getId() == currentUser.getId();
			boolean isManager = currentUser.getRole() == "MANAGER";
			if (isTheirs || isManager) {
				orderService.updateOrder(order, orderId);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

			}
		}

	}

	@DeleteMapping("/{orderId}") // Delete a customers order
	@PreAuthorize("isAuthenticated()")
	public void deleteOrder(@PathVariable long orderId, Authentication authUser) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		Optional<Order> theOrder = orderService.getOrderById(orderId);
		if (theOrder.isPresent()) {
			Order theOrder2 = theOrder.get();
			boolean isTheirs = theOrder2.getCustomer().getId() == currentUser.getId();
			boolean isManager = currentUser.getRole() == "MANAGER";
			if (isTheirs || isManager) {
				orderService.deleteOrder(orderId);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

			}
		}
	}

}
