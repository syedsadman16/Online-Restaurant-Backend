package com.cs322.ors.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.cs322.ors.db.*;
import com.cs322.ors.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

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

	@Autowired
	TabooWordRepository tabooWordsRepository;
	
	@Autowired
	UserWarningRepository userWarningRepository;

	@Autowired
	UserRatingRepository userRatingRepository;


	@Override
	public void run(String... args) throws Exception {

		// REAL DATA NEEDED TO INITIALIZE APPLICATION
		User manager = new User("manager", "manager", "MANAGER");

		// DUMMY DATA
		User customer1 = new User("customer1", "customer1", "CUSTOMER");
		User customer2 = new User("customer2", "customer2", "CUSTOMER");
		customer2.setClosed(true);
		User vip1 = new User("vip1", "vip1", "VIP");
		User chef1 = new User("chef1", "chef1", "CHEF");
		User deliverer1 = new User("deliverer1", "deliverer1", "DELIVERER");
		List<User> users = Arrays.asList(manager, customer1, vip1, chef1, deliverer1,customer2);
		userRepository.saveAll(users);
		
		Dish dish1 = new Dish("strawberry cake", chef1, null, null, BigDecimal.valueOf(10), false);
		Dish dish2 = new Dish( "RED velvet cake", chef1, null, null, BigDecimal.valueOf(10.50), true);
		dishRepository.saveAll(Arrays.asList(dish1,dish2));
		
		Order order1 = new Order(customer1, 0);
		orderRepository.save(order1);
		
		Order order2 = new Order(customer1, 0);
		orderRepository.save(order2);
		
		Order order3 = new Order(customer2, 0);
		orderRepository.save(order3);		

		Order order4 = new Order(customer1, 0);
		orderRepository.save(order4);	
		
		ChefJob chefjob1 = new ChefJob(chef1, order2);
		chefJobRepository.save(chefjob1);
		DishOrder dishOrder1 = new DishOrder(dish1, order1, 2);
		dishOrderRepository.save(dishOrder1);

		TabooWord tabooWord1 = new TabooWord("Jackass");
		tabooWordsRepository.save(tabooWord1);

		UserWarning strike1= new UserWarning(customer1, "1st strike");
		userWarningRepository.save(strike1);

		UserRating rating1 = new UserRating(4, customer1, deliverer1, dishOrder1);
		userRatingRepository.save(rating1);

	}
}
