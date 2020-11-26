package com.cs322.ors.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.Dish;
import com.cs322.ors.service.DishService;

@RestController

public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@RequestMapping("api/menu")
	public List<Dish> getAllDishes(){
		return dishService.getAllDishes();
	}
	
	@RequestMapping("/Menu/keyword/{keyword}")
	public List<Dish> getAllDishesByKeyWord(@PathVariable String keyword){
		return dishService.getAllDishesByWord(keyword);
	}
		
	@RequestMapping("/Menu/{dishId}")
	public Optional<Dish> getDish(@PathVariable long dishId) {
		return dishService.getDish(dishId);
	}
	
	@PreAuthorize("hasAnyRole('CHEF', 'MANAGER')")
	@RequestMapping(method = RequestMethod.POST, value = "/Menu")
	public void addDish(@Valid @RequestBody Dish dish) {
		dishService.addDish(dish);
	}
	
	@PreAuthorize("hasAnyRole('CHEF', 'MANAGER')")
	@RequestMapping(method = RequestMethod.PUT, value = "/Menu/{dishId}")
	public void updateDish(@RequestBody Dish dish,@PathVariable long dishId) {
		dishService.updateDish(dish,dishId);
	}
	
	@PreAuthorize("hasAnyRole('CHEF', 'MANAGER')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/Menu/{dishId}")
	public void deleteDish(@PathVariable long dishId) {
		dishService.deleteDish(dishId);
	}
	
	


}
