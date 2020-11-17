package com.cs322.ors.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

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

import com.cs322.ors.model.DishOrder;
import com.cs322.ors.model.Order;
import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@GetMapping("/Orders")   //Get all customer own orders or all orders 
	@PreAuthorize("isAuthenticated()")
	public List<Order> getOrders(Authentication authUser){
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		if(currentUser.getRole() == "MANAGER") {
			return orderService.getAllOrders();
		}else {
			return orderService.getOrderByUser(currentUser.getId());
		}
		
	}
	
	@GetMapping("/Orders/{orderId}")	//Get customers order by id	
	@PreAuthorize("isAuthenticated()") 			
	public Order getOrderWithId(@PathVariable long orderId, Authentication authUser, HttpServletResponse response){		
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		Optional<Order> order = orderService.getOrderById(orderId);
		if(order.isPresent()) {
			Order theOrder = order.get();
			boolean isTheirs = theOrder.getCustomer().getId() == currentUser.getId();
			boolean isManager = currentUser.getRole() == "MANAGER"; 
			if(isTheirs || isManager) {
				return theOrder;
			}else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return null;
			}
		} else {
			return null;
		}		
	}

	@PostMapping("/Orders")
	@PreAuthorize("hasAnyRole('CUSTOMER','VIP','MANAGER')")
	public void makeOrder(@Valid @RequestBody Order order, Authentication authUser, HttpServletResponse response){		
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		boolean isVIP = currentUser.getRole() == "VIP"; 
		boolean isCustomer = currentUser.getRole() == "CUSTOMER" || isVIP; 
		List<DishOrder> dishesOrders =  order.getDishOrders();
		boolean isDishesSpecial = dishesOrders.stream().anyMatch(dishesOrder -> dishesOrder.getDish().isSpecial() == true);
		boolean canOrderDishTypes = (isVIP && isDishesSpecial) || (isCustomer && !isDishesSpecial);
		if(canOrderDishTypes) {
			orderService.makeOrder(order);
			//TODO: Get sum of dishOrders to compare with user's balance. THe sum will also use DiscountService for the final amount. If we have enough money continue. Else error response.
				//TODO: Make an transaction for the final amount.
				//TODO: Manually update the user's balance by the final sum.
			
		}else {
			response.setStatus(HttpStatus.UNAUTHORIZED.value(), "Can not order special dishes");
		}	
	}
	
	@PutMapping("/Orders/{orderId}")  //Update a customers order
	@PreAuthorize("isAuthenticated()")
	public void updateOrder(@Valid @RequestBody Order order,@PathVariable long orderId, Authentication authUser, HttpServletResponse response) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		Optional<Order> theOrder = orderService.getOrderById(orderId);
		if(theOrder.isPresent()) {
			Order theOrder2 = theOrder.get();
			boolean isTheirs = theOrder2.getCustomer().getId() == currentUser.getId();
			boolean isManager = currentUser.getRole() == "MANAGER"; 
			if(isTheirs || isManager) {
				orderService.updateOrder(order,orderId);
			}else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());				
			}
		}	
		
	}
	
	@DeleteMapping("/Orders/{orderId}")   //Delete a customers order
	@PreAuthorize("isAuthenticated()") 	
	public void deleteOrder(@PathVariable long orderId, Authentication authUser, HttpServletResponse response) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		Optional<Order> theOrder = orderService.getOrderById(orderId);
		if(theOrder.isPresent()) {
			Order theOrder2 = theOrder.get();
			boolean isTheirs = theOrder2.getCustomer().getId() == currentUser.getId();
			boolean isManager = currentUser.getRole() == "MANAGER"; 
			if(isTheirs || isManager) {
				orderService.deleteOrder(orderId);
			}else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());				
			}
		}
	}
	
	
	
	
	
	
	
	
}
