package com.cs322.ors.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/Orders/{id}")
	@PreAuthorize("hasAnyRole('CUSTOMER','VIP')")  // do it for each user id so they can see their own orders
	public Optional<Order> getOrdersWithId(@PathVariable long id){
		return orderService.getOrder(id);
		
	}
	
	//make it so each user can get their orders.
}
