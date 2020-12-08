package com.cs322.ors.db;

import com.cs322.ors.model.DishRating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRatingRepository extends JpaRepository<DishRating, Long> {
	List<DishRating> findByCritic_id(long customerId);
} 
