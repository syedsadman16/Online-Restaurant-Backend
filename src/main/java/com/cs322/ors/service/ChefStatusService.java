package com.cs322.ors.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.model.Dish;
import com.cs322.ors.model.DishRating;
import com.cs322.ors.model.User;

@Service
public class ChefStatusService {
	
	/* use case 2 for chefs 
	 * Consistently low rating = demotion, 
	 * Consistently high rating = promotion)
	 */
	
	@Autowired
	DishRatingService dishRatingService;
	
	@Autowired
	DishService dishService;
	
	@Autowired
	SalaryService salaryService;
	
	@Autowired
	UserService userService;
	
	public List<Double> averageRating(User chef) {  // average rating on this chef's dishes (all rating of all his dishes combined and averaged)

		List<Double> allRatings = new ArrayList<>();
		if (chef.getRole() == "CHEF") {
			List<Dish> chefDishes = dishService.getDishesbyChef(chef.getId());
			
			 allRatings = chefDishes.stream()				// list of all ratings of dishes made by the chef
										.filter(dishes -> dishes != null)
										.map(dishes -> dishRatingService.getDishRatings(dishes.getId()))
										.filter(dishRatings -> dishRatings != null)
										.flatMap(Collection::stream)
										.map(ratings -> ratings.getRating())
										.collect(Collectors.toList());
										
			 
//			for(Dish dishes : chefDishes) {
//				for (DishRating rating : dishRatingService.getDishRatings(dishes.getId())) {
//					allRatings.add(rating.getRating());
//				}
//			}
//												
//		}
		
			
		}
		double sum = 0;
		if	(!allRatings.isEmpty()) {
			for (double ratings:allRatings) {
				 sum += ratings;
			}
		}
		double average = sum/allRatings.size();
		double length = allRatings.size();
		List<Double> returnElements = new ArrayList<Double>();
		returnElements.add(average);
		returnElements.add(length);
		
		//returns [averageRating, how many total ratings]
		return returnElements;
	}
	
	
	public void promoteOrDemoteChef(User chef) {
		List<Double> ratingInfo = averageRating(chef);
		
		//ratingInfo[0] = averageRating
		//ratingInfo[1] = total ratings
		
		int counter = 0;		// keeping count of how many demotions
		int goodCounter = 0;	// count of how many promotions
		double ratingChecker = ratingInfo.get(1) % 20;
		BigDecimal currentSalary = salaryService.getSalaryByUser(chef.getId()).get(0).getAmount();
		
		if (counter<2 && ratingChecker != counter) {
			if( ratingChecker >= 1  && ratingInfo.get(0) < 2.5 ) {
				salaryService.updateSalary(currentSalary.subtract(BigDecimal.valueOf(1000)), chef.getId());
				counter += 1;
			}
		}
		if(ratingChecker != goodCounter){
			if( ratingChecker >= 1 && ratingInfo.get(0) >= 4.0) {
				salaryService.updateSalary(currentSalary.add(BigDecimal.valueOf(1000)), chef.getId());
				goodCounter += 1;
			}
		}
		
		if (counter >= 2 + goodCounter) { //if their demotions exceed their promotions + 2
			// then fire
			userService.deleteUser(chef.getId());
		}
			
	}
}
	
	



