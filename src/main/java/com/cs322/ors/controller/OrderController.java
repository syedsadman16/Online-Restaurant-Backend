package com.cs322.ors.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.Order;
import com.cs322.ors.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@GetMapping("/Orders")   //make it so managers can see all orders.
	@PreAuthorize("hasRole('MANAGER')")  
	public List<Order> getOrders(){
		return orderService.getAllOrders();
	}
	
	@GetMapping("/Orders/{customerId}")										//principal is the user currently logged in
	@PreAuthorize("#customerId == principal.user.id OR hasRole('MANAGER')")  				// do it for each user id so they can see their own orders
	public List<Order> getOrdersWithId(@PathVariable long customerId){		// gave manager perms for testing purposes
		return orderService.getOrderByUser(customerId);
	}
	
	@GetMapping("/Orders/{customerId}/{orderId}")										//principal is the user currently logged in
	@PreAuthorize("#customerId == principal.user.id OR hasRole('MANAGER')")  				// do it for each user id so they can see their own orders
	public Optional<Order> getOrdersWithId(@PathVariable long customerId,@PathVariable long orderId){		// gave manager perms for testing purposes
		return orderService.getOrderByUserById(orderId);
	}

	@PostMapping("/Orders")
	@PreAuthorize("hasAnyRole('CUSTOMER','VIP','MANAGER')")
	public void makeOrder(@Valid @RequestBody Order order) {
		orderService.makeOrder(order);
	}
	
	@PutMapping("/Orders/{orderId}")
	@PreAuthorize("hasAnyRole('CUSTOMER','VIP','MANAGER')")
	public void updateOrder(@Valid @RequestBody Order order,@PathVariable long orderId) {
		orderService.updateOrder(order,orderId);
	}
	
	@DeleteMapping("/Orders/{orderId}")   //maybe go through /orders/{customerId}/{orderId} for extra security?
	@PreAuthorize("hasAnyRole('CUSTOMER','VIP','MANAGER')")
	public void deleterOrder(@PathVariable long orderId) {
		orderService.deleteOrder(orderId);
	}
	
	
	
	
	
	
	
	
}
