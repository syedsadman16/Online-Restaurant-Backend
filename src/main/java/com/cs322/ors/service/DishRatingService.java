package com.cs322.ors.service;

import com.cs322.ors.db.DishRatingRepository;
import com.cs322.ors.model.DishRating;
import com.cs322.ors.model.TabooWord;
import com.cs322.ors.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Handle all the crud methods of a dish rating. Can be accessed using id
 * Surfers should be able to view all ratings of a dish
 * Customers can create ratings for a specific dish they've ordered
 * Chefs can only get ratings for their assoicated dishes
 */
@Service
public class DishRatingService {

    @Autowired
    private DishRatingRepository dishRatingRepository;

    public List<DishRating> getAllDishRatings(){
        return dishRatingRepository.findAll();
    }

    public DishRating getSpecificDishRating(Long id){
        return dishRatingRepository.findById(id).get();
    }

    /*
     * DishRating should only be created for an existing Dish
     */
    public void createDishRating(DishRating dishRating){
        dishRatingRepository.save(dishRating);
    }

    public void updateDishRating(DishRating updatedRating){
        dishRatingRepository.save(updatedRating);
    }

    public void deleteUserRating(long id) {
        Optional<DishRating> rating = this.dishRatingRepository.findById(id);
        dishRatingRepository.delete(rating.get());
    }


}
