package com.cs322.ors.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs322.ors.db.DishRepository;
import com.cs322.ors.model.Dish;

@Service
public class DishService {
	@Autowired
	private DishRepository dishRepository;
	
	@RequestMapping("/Menu")
	public List<Dish> getAllDishes(){
		List<Dish> dishes = new ArrayList<>();
		dishRepository.findAll().forEach(dishes::add);
		return dishes;
	}

	public Dish getDish(int Id) {
		return dishRepository.findBydishId(Id);
	}

	public void addDish(Dish dish) {
		dishRepository.save(dish);
		
	}

	public void updateDish(Dish dish) {
		dishRepository.save(dish);
		
	}

	public void deleteDish(Dish dish) {
		dishRepository.delete(dish);
	}
}
