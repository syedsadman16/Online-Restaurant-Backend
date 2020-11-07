package com.cs322.ors.db;

import com.cs322.ors.model.DishRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRatingRepository extends JpaRepository<DishRating, Long> {
}
