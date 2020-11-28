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
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	DiscussionRepository discussionRepository;
	
	@Autowired
	CommentService commentService;
	
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

	@Autowired
	TransactionRepository TransactionRepository;

	@Autowired
	DishKeyWordRepository dishKeyWordRepository;

	@Autowired
	DeliveryJobsService deliveryJobsService;

	@Autowired
	ClaimsService claimsService;
	
	@Autowired
	SalaryRepository salaryRepository;
	@Override
	public void run(String... args) throws Exception {

		// REAL DATA NEEDED TO INITIALIZE APPLICATION
		User manager = new User("manager", "manager", "MANAGER");

		// DUMMY DATA
		User customer1 = new User("customer1", "customer1", "CUSTOMER");
		customer1.setVerified(true);
		User customer2 = new User("customer2", "customer2", "CUSTOMER");
		customer2.setClosed(true);
		User customer3 = new User("customer3", "customer3", "CUSTOMER");
		User vip1 = new User("vip1", "vip1", "VIP");
		User chef1 = new User("chef1", "chef1", "CHEF");
		User chef2 = new User("chef2", "chef2", "CHEF");
		User deliverer1 = new User("deliverer1", "deliverer1", "DELIVERER");
		vip1.setVerified(true);
		chef1.setVerified(true);
		chef2.setVerified(true);
		deliverer1.setVerified(true);

		List<User> users = Arrays.asList(manager, customer1, vip1, chef1, deliverer1,customer2,customer3);
		userService.createUser(customer1);
		userService.createUser(customer2);
		userService.createUser(customer3);
		userService.createUser(vip1);
		userService.createUser(chef1);
		userService.createUser(chef2);
		userService.createUser(deliverer1);

		
		Dish dish1 = new Dish("strawberry cake", chef1, "Strawberry falvored cake", null, BigDecimal.valueOf(10), false);
		Dish dish2 = new Dish( "RED velvet cake", chef1, "choclate layered cake", null, BigDecimal.valueOf(10.50), true);
		Dish dish3 = new Dish( "Tiramisu cake", chef1, "coffee falvored", null, BigDecimal.valueOf(10.50), false);

		// Add test dish ratings
		DishRating rating1 = new DishRating(4.3, customer1,dish2);
		rating1.setComments("I like red velvet cakes");
		DishRating rating2 = new DishRating(2.5, customer2,dish2);
		rating2.setComments("What a donut");
		dish2.addToRatings(rating2);
		dish2.addToRatings(rating1);
		dishRepository.saveAll(Arrays.asList(dish1,dish2,dish3));

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
		
		ChefJob chefjob2 = new ChefJob(chef2, order1);
		chefJobRepository.save(chefjob2);
		
		ChefJob chefjob3 = new ChefJob(chef2, order4);
		chefjob3.setCompleted(true);
		chefJobRepository.save(chefjob3);
		
		DishOrder dishOrder1 = new DishOrder(dish1, order1, 2);
		dishOrderRepository.save(dishOrder1);

		TabooWord tabooWord1 = new TabooWord("Jackass");
		tabooWordsRepository.save(tabooWord1);
		


		UserWarning strike1= new UserWarning(customer1, "1st strike");
		userWarningRepository.save(strike1);
		
//		UserWarning strike2= new UserWarning(customer1, "2nd strike");
//		userWarningRepository.save(strike2); 
		
		DishKeyWord word1 = new DishKeyWord("Dessert",dish1,chef1);
		dishKeyWordRepository.save(word1);
		
		DishKeyWord word2 = new DishKeyWord("Spicy",dish1,chef1);
		dishKeyWordRepository.save(word2);
		
		DishKeyWord word3 = new DishKeyWord("Spicy",dish2,chef1);
		dishKeyWordRepository.save(word3);
		
		DishKeyWord word4 = new DishKeyWord("Cake",dish3,chef1);
		dishKeyWordRepository.save(word4);

		// Add test user ratings
		UserRating rating3 = new UserRating(1, "Customer was uncooperative", deliverer1, order1);
		customer1.addToRatings(rating3);
		userRepository.saveAll(users);
		
		Transaction transaction1 = new Transaction(customer1, BigDecimal.valueOf(32.20) ,"OrderId: 1", 0);
		TransactionRepository.save(transaction1);

//		Salary s1 = new Salary(chef1, BigDecimal.valueOf(20000), "sous chef");
//		salaryRepository.save(s1);
		
//		Salary s2 = new Salary(deliverer1, BigDecimal.valueOf(10000), "delivery man");
//		salaryRepository.save(s2);
		
		// Delivery Jobs
		DeliveryJobs job1 = new DeliveryJobs(order1);
		DeliveryJobs job2 = new DeliveryJobs(order2);
		deliveryJobsService.addDeliveryJob(job1);
		deliveryJobsService.addDeliveryJob(job2);

		// Claims
		Claims claim1 = new Claims(rating3, customer1, "Fake news");
		claimsService.postClaim(claim1);
		
		//Discussions
		Discussion discussion1 = new Discussion(customer1, "Who the best chef in here?");
		discussionRepository.save(discussion1);
		Comment comment1 = new Comment(discussion1, customer2, "You ackass"); 
		commentService.createComment(comment1, customer2);
		
		
	}
}
