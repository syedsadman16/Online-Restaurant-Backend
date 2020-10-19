package com.cs322.ors.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs322.ors.model.Dish;

public interface DishRepository extends JpaRepository<Dish,Long> {
	
	
}
