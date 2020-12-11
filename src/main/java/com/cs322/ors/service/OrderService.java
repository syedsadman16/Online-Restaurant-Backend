package com.cs322.ors.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.OrderRepository;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.DishOrder;
import com.cs322.ors.model.Order;
import com.cs322.ors.model.User;

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

//	public void makeOrder(Order order) {
//		orderRepository.save(order);
//	}

	public Order makeOrder(User customer, List<DishOrder> dishOrders, int orderType) {
		Order order = new Order(customer, orderType);
		dishOrders.stream().forEach(dishOrder -> dishOrder.setOrder(order));
		order.setDishOrders(dishOrders);		
		orderRepository.save(order);
		return order;
	}

	public void updateOrder(Order orderUpdate,long orderId) {
		Optional<Order> OrderDB = this.orderRepository.findById(orderId);
		
		if(OrderDB.isPresent()) {
			orderRepository.save(orderUpdate);
		}
	}
	
//	public Dish commonOrder(long userId) {
//		List<Dish> allDishesOrdered = new ArrayList<Dish>();
//		getOrderByUser(userId).stream()
//				.forEach(Order -> Order.getDishOrders()
//				.forEach(dishOrder -> dishOrder.)).
//				.
//		
//	}

	public void deleteOrder(long orderId) {
		orderRepository.deleteById(orderId);    //throws illegalArgumentException if null
	}

	public Optional<Order> getOrderById(long orderId) {
		return orderRepository.findById(orderId);
	}

	public void setCompleted(long id) {
		Optional <Order> orderDB = orderRepository.findById(id);
		
		if (orderDB.isPresent()) {
			orderDB.get().setCompleted(true);
			orderRepository.save(orderDB.get());
		}
	}

	public void setDishRated(long orderId) {
		Optional <Order> orderDB = orderRepository.findById(orderId);
		
		if (orderDB.isPresent()) {
			orderDB.get().setDishRated(true);
			orderRepository.save(orderDB.get());
		}
		
	}
	
	public void setDelivererRated(long orderId) {
		Optional <Order> orderDB = orderRepository.findById(orderId);
		
		if (orderDB.isPresent()) {
			orderDB.get().setDelivererRated(true);
			orderRepository.save(orderDB.get());
		}
		
	}

	public void setUserRated(long orderId) {
		Optional <Order> orderDB = orderRepository.findById(orderId);
		
		if (orderDB.isPresent()) {
			orderDB.get().setUserRated(true);
			orderRepository.save(orderDB.get());
		}
		
	}
	
	

	
	

}
