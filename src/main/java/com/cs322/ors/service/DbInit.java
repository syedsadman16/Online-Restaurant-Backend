package com.cs322.ors.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.ChefJobRepository;
import com.cs322.ors.db.DishOrderRepository;
import com.cs322.ors.db.DishRepository;
import com.cs322.ors.db.OrderRepository;
import com.cs322.ors.db.UserRepository;
import com.cs322.ors.model.ChefJob;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.DishOrder;
import com.cs322.ors.model.Order;
import com.cs322.ors.model.User;

@Service
public class DbInit implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ChefJobRepository chefJobRepository;
	
	@Autowired
	DishRepository dishRepository;
	
	@Autowired
	DishOrderRepository dishOrderRepository;


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
		
		Dish dish1 = new Dish(chef1, null, null, BigDecimal.valueOf(10), "strawberry cake");
		Dish dish2 = new Dish(chef1, null, null, BigDecimal.valueOf(10.50), "RED velvet cake");
		dishRepository.saveAll(Arrays.asList(dish1,dish2));
		
		Order order1 = new Order(customer1, 0);
		orderRepository.save(order1);
		
		ChefJob chefjob1 = new ChefJob(chef1, order1);
		chefJobRepository.save(chefjob1);

		DishOrder dishOrder1 = new DishOrder(dish1, order1, 2);
		dishOrderRepository.save(dishOrder1);

	}
}
