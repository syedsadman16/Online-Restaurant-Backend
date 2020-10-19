package com.cs322.ors.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs322.ors.db.DishRepository;
import com.cs322.ors.model.Customer;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.Test;

@Service
public class DishService {
	private static final Dish NULL = null;
	@Autowired
	private DishRepository dishRepository;
	
	public List<Dish> getAllDishes(){
		return dishRepository.findAll();
	}

	public Optional<Dish> getDish(long Id) {
		return dishRepository.findById(Id);
	}

	public void addDish(Dish dish) {
		dishRepository.save(dish);
	}

	public void updateDish(Dish dish,long Id) {
		Dish updatedDish = dishRepository.findById(Id).orElseThrow();
		updatedDish.setChefID(dish.getChefID());
		updatedDish.setChefName(dish.getChefName());
		updatedDish.setDescription(dish.getDescription());
		updatedDish.setImageUrl(dish.getImageUrl());
		updatedDish.setPrice(dish.getPrice());
		updatedDish.setTitle(dish.getTitle());
		
		dishRepository.save(updatedDish);
	}

	public void deleteDish(Dish dish) {
		dishRepository.delete(dish);
	}
}
