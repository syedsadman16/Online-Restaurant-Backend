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

	public List<Order> getOrderByUser(long id) {
		return orderRepository.findByCustomer_Id(id);
	}

	public void makeOrder(Order order) {
		orderRepository.save(order);
	}

	public void updateOrder(Order orderUpdate,long orderId) {
		Optional<Order> OrderDB = this.orderRepository.findById(orderId);
		
		if(OrderDB.isPresent()) {
			orderUpdate.setId(orderId);
			orderRepository.save(orderUpdate);
		}
	}

	public void deleteOrder(long orderId) {
		orderRepository.deleteById(orderId);    //throws illegalArgumentException if null
	}

	public Optional<Order> getOrderById(long orderId) {
		return orderRepository.findById(orderId);
	}
	
	

	
	

}
