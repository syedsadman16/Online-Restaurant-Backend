package com.cs322.ors.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cs322.ors.model.TabooWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.DishKeyWordRepository;
import com.cs322.ors.db.DishRepository;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.DishKeyWord;
import com.cs322.ors.model.Order;

@Service
public class DishService {
	private static final Dish NULL = null;
	
	@Autowired
	private DishRepository dishRepository;
	
	@Autowired
	private DishKeyWordRepository dishKeyWordRepository;
	
	public List<Dish> getAllDishes(){
		return dishRepository.findAll();
	}

	public List<Dish>getDishesbyChef(long chefId){
		return dishRepository.findByChef_id(chefId);
	}
	public Optional<Dish> getDish(long Id) {
		return dishRepository.findById(Id);
	}

	public Dish addDish(Dish dish) {
		return dishRepository.save(dish);
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

	public List<Dish> getAllDishesByWord(String keyWord) {
		List<DishKeyWord> keyWordDishes =  dishKeyWordRepository.findByKeyWord(keyWord);
		List<Dish> Dishes = new ArrayList<Dish>();
		if (keyWordDishes.isEmpty()) {
			return Dishes;
		}
		else {
			for(int i = 0; i < keyWordDishes.size(); i++) {		//goes through the list of dishes with the particular keyword and converts keywords to dishes.
				Dishes.add(keyWordDishes.get(i).getDish());
			}
			return Dishes;
		}
	}
}
