package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import com.cs322.ors.model.TabooWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs322.ors.db.DishRepository;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.Order;

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
		Optional<Dish> DishDB = this.dishRepository.findById(Id);
		
		if(DishDB.isPresent()) {
			dish.setId(Id);
			dishRepository.save(dish);
		}
	}

	public void deleteDish(long dishid) {
		dishRepository.deleteById(dishid);
	}
}
