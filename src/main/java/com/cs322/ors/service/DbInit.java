package com.cs322.ors.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.OrderRepository;
import com.cs322.ors.db.UserRepository;
import com.cs322.ors.model.Order;
import com.cs322.ors.model.User;

@Service
public class DbInit implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {

		// REAL DATA NEEDED TO INITIALIZE APPLICATION
		User manager = new User("manager", "manager", "MANAGER");

		// DUMMY DATA
		User customer1 = new User("customer1", "customer1", "CUSTOMER");
		User vip1 = new User("vip1", "vip1", "VIP");
		User chef1 = new User("chef1", "chef1", "CHEF");
		User deliverer1 = new User("deliverer1", "deliverer1", "DELIVERER");
		List<User> users = Arrays.asList(manager, customer1, vip1, chef1, deliverer1);
		userRepository.saveAll(users);
		
		Order order1 = new Order(customer1, 0);
		orderRepository.save(order1);

	}
}
