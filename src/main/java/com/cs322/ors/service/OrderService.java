package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.OrderRepository;
import com.cs322.ors.model.Order;

@Service
public class OrderService {
	
	@Autowired
	public OrderRepository orderRepository;

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Order> getOrder(long id) {
		return orderRepository.findById(id);
		// TODO Auto-generated method stub
		
	}

	
	

}
