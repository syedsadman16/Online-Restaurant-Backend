package com.cs322.ors.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.DishOrderRepository;
import com.cs322.ors.db.DishRepository;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.DishOrder;

@Service
public class DishOrderService {
	
	@Autowired
	DishOrderRepository dishOrderRepository;
	
	@Autowired
	DishRepository dishRepository;
	
	public List<DishOrder> getAllDishOrders(){
		return dishOrderRepository.findAll();
	}
	
	
	public List<Dish> topThreeOrderedDishes(){
		List<DishOrder> dishOrderDB = getAllDishOrders();
		Map<Long ,Integer> map = new HashMap<>();
		
		
		for (DishOrder dish : dishOrderDB) {    //counting frequency of orders received by each dish
			if (map.containsKey(dish.getDish().getId())){
				map.put(dish.getDish().getId(), map.get(dish.getDish().getId()) + 1);
			}
			else {
				map.put(dish.getDish().getId(), 1);
			}
		}
		
		List<Long> mostCommon = map
					//stream as set
					.entrySet().stream()
	                // Sort by value.
	                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue())) 
	                // Keys only.
	                .map(e -> e.getKey())
	                // As a list.
	                .collect(Collectors.toList());
		
		List<Dish> topThreeDishes = new ArrayList<Dish>();
		for(long id : mostCommon.subList(0, 3)) {
			topThreeDishes.add(dishRepository.findById(id).get());
		}
		
		return topThreeDishes;
		
	}

}
