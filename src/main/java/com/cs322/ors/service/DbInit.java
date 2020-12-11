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
	TransactionRepository TransactionRepository;

	@Autowired
	DishKeyWordRepository dishKeyWordRepository;

	@Autowired
	DeliveryJobsService deliveryJobsService;

	@Autowired
	ClaimsService claimsService;

	@Autowired
	TableRepository tableRepository;

	@Autowired
	UserRatingsRepository userRatingsRepository;

	@Override
	public void run(String... args) throws Exception {
		try {

			// REAL DATA NEEDED TO INITIALIZE APPLICATION
			User manager = new User("manager", "manager", "MANAGER");
			RestaurantTable table1 = new RestaurantTable(1);
			RestaurantTable table2 = new RestaurantTable(2);
			RestaurantTable table3 = new RestaurantTable(3);
			RestaurantTable table4 = new RestaurantTable(4);
			RestaurantTable table5 = new RestaurantTable(5);
			List<RestaurantTable> tables = Arrays.asList(table1, table2, table3, table4, table5);
			userService.createUser(manager, null, null);
			tableRepository.saveAll(tables);

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
			User deliverer2 = new User("deliverer2", "deliverer2", "DELIVERER");
			vip1.setVerified(true);
			chef1.setVerified(true);
			chef2.setVerified(true);
			deliverer1.setVerified(true);
			deliverer2.setVerified(true);
			

			userService.createUser(customer1, new CustomerInfo("address", "name", customer1), null);
			userService.createUser(customer2, new CustomerInfo("address", "name", customer2), null);
			userService.createUser(customer3, new CustomerInfo("address", "name", customer3), null);
			userService.createUser(vip1, new CustomerInfo("address", "name", vip1), null);
			userService.createUser(chef1, null, new EmployeeInfo("address", "name", chef1));
			userService.createUser(chef2, null, new EmployeeInfo("address", "name", chef2));
			userService.createUser(deliverer1, null, new EmployeeInfo("address", "name", deliverer1));
			userService.createUser(deliverer2, null, new EmployeeInfo("address", "name", deliverer2));

			Dish dish1 = new Dish("strawberry cake", chef1, "Strawberry falvored cake",
					"https://static.onecms.io/wp-content/uploads/sites/24/2017/02/474428695_5367210460001_5365952845001-vs.jpg",
					BigDecimal.valueOf(10), false);
			Dish dish2 = new Dish("RED velvet cake", chef1, "choclate layered cake",
					"https://food-images.files.bbci.co.uk/food/recipes/red_velvet_cake_70743_16x9.jpg",
					BigDecimal.valueOf(10.50), true);
			Dish dish3 = new Dish("Tiramisu cake", chef1, "coffee falvored",
					"https://i.pinimg.com/originals/28/45/94/284594d16a876fa48dd8b660d8cfe327.jpg",
					BigDecimal.valueOf(10.50), false);
			Dish dish4 = new Dish("Hershey Pie", chef2,
					"Chocolate mousse layered with brownies and topped with whipped cream",
					"https://hips.hearstapps.com/del.h-cdn.co/assets/17/45/1510259607-delish-hot-chocolate-pie-2.jpg", BigDecimal.valueOf(8.75), false);
			Dish dish5 = new Dish("Boston Cream Mousse Cheesecake", chef2,
					"Cheesecake combination: cool, creamy custard nestled in fluffy cake & topped with bittersweet chocolate",
					"https://truffle-assets.imgix.net/pxqrocxwsjcc_77cCHKK1s46C8mEwqqgqic_chocolate-snowflake-mousse-cake_landscapeThumbnail_en.jpeg",
					BigDecimal.valueOf(11.25), true);

			// Add test dish ratings
			DishRating rating1 = new DishRating(4.3, "I like red velvet cakes", customer1, dish2);
			DishRating rating2 = new DishRating(2.5, "Too much red, not enough velvet", customer2, dish2);
			dish2.addToRatings(rating2);
			dish2.addToRatings(rating1);
			dish1.addToRatings(new DishRating(4, "It the fruit that counts", customer2, dish1));
			dish4.addToRatings(new DishRating(1, "Diabetes", customer2, dish4));
			dish5.addToRatings(new DishRating(5, "Taste of paradise", customer1, dish5));
			dishRepository.saveAll(Arrays.asList(dish1, dish2, dish3, dish4, dish5));

			Order order1 = new Order(customer1, 0);
			orderRepository.save(order1);

			Order order2 = new Order(customer1, 0);
			orderRepository.save(order2);

			Order order3 = new Order(customer2, 0);
			orderRepository.save(order3);

			Order order4 = new Order(customer1, 0);
			orderRepository.save(order4);

			ChefJob chefjob1 = new ChefJob(chef1, order1);
			chefJobRepository.save(chefjob1);

			ChefJob chefjob2 = new ChefJob(chef2, order2);
			chefJobRepository.save(chefjob2);

			ChefJob chefjob3 = new ChefJob(chef2, order4);
//	chefjob3.setCompleted(true);
			chefJobRepository.save(chefjob3);

			DishOrder dishOrder1 = new DishOrder(dish1, order1, 2);
			dishOrderRepository.save(dishOrder1);

			DishOrder dishOrder2 = new DishOrder(dish4, order2, 3);
			dishOrderRepository.save(dishOrder2);

			DishOrder dishOrder3 = new DishOrder(dish5, order4, 3);
			dishOrderRepository.save(dishOrder3);

			DishOrder dishOrder4 = new DishOrder(dish4, order3, 3);
			dishOrderRepository.save(dishOrder4);

			TabooWord tabooWord1 = new TabooWord("Jackass");
			tabooWordsRepository.save(tabooWord1);

			UserWarning strike1 = new UserWarning(customer1, "1st strike");
			userWarningRepository.save(strike1);

//	UserWarning strike2= new UserWarning(customer1, "2nd strike");
//	userWarningRepository.save(strike2); 

			DishKeyWord word1 = new DishKeyWord("Dessert", dish1, chef1);
			dishKeyWordRepository.save(word1);

			DishKeyWord word2 = new DishKeyWord("Spicy", dish1, chef1);
			dishKeyWordRepository.save(word2);

			DishKeyWord word3 = new DishKeyWord("Mousse", dish2, chef1);
			dishKeyWordRepository.save(word3);

			DishKeyWord word4 = new DishKeyWord("Cake", dish3, chef1);
			dishKeyWordRepository.save(word4);

			DishKeyWord word5 = new DishKeyWord("Cheesecake", dish4, chef1);
			dishKeyWordRepository.save(word5);

			DishKeyWord word6 = new DishKeyWord("Pie", dish5, chef1);
			dishKeyWordRepository.save(word6);

			DishKeyWord word7 = new DishKeyWord("Shake", dish5, chef1);
			dishKeyWordRepository.save(word7);

			UserRatings rating3 = new UserRatings(2.1, "Customer upset over missing drink", customer1, order1, 0);
			userRatingsRepository.save(rating3);

			UserRatings rating4 = new UserRatings(0.9, " missing ", customer2, order2, 0);
			userRatingsRepository.save(rating4);

			Transaction transaction1 = new Transaction(customer1, BigDecimal.valueOf(32.20), "OrderId: 1", 0);
			TransactionRepository.save(transaction1);

//	Salary s1 = new Salary(chef1, BigDecimal.valueOf(20000));
//	salaryRepository.save(s1);
//	
//	Salary s2 = new Salary(deliverer1, BigDecimal.valueOf(10000));
//	salaryRepository.save(s2);

			// Delivery Jobs
			DeliveryJobs job1 = new DeliveryJobs(order1);
			DeliveryJobs job2 = new DeliveryJobs(order2);
			deliveryJobsService.addDeliveryJob(job1);
			deliveryJobsService.addDeliveryJob(job2);

			// Claims
//			Claims claim1 = new Claims(rating3, customer1, "Fake news");
//			claimsService.postClaim(claim1);
//
//			Claims claim2 = new Claims(rating4, customer2, "Fake ");
//			claimsService.postClaim(claim2);

			// Discussions
			Discussion discussion1 = new Discussion(customer1, "Who the best chef in here?");
			discussionRepository.save(discussion1);
			Comment comment1 = new Comment(discussion1, customer2, "You ackass");
			commentService.createComment(comment1, customer2);

		} catch (Exception e) {

			System.out.println(e);
		}

	}
}
