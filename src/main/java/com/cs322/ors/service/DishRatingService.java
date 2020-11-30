package com.cs322.ors.service;

import com.cs322.ors.db.DishRepository;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.DishRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Handle all the crud methods of a dish rating. Can be accessed using id
 * Surfers should be able to view all ratings of a dish
 * Customers can create ratings for a specific dish they've ordered
 * Chefs can only get ratings for their assoicated dishes
 */
@Service
public class DishRatingService {

    // @Autowired
    // private DishRatingRepository dishRatingRepository;
    @Autowired
    private DishRepository dishRepository; 

    /*
     * Returns Dish objects that each contain an array of ratings
     */
    public List<Dish> getAllDishes(){
        return dishRepository.findAll();
    }

    /*
     * Return all ratings from a Dish
     */
    public List<DishRating> getDishRatings(Long id){
        Dish dish = dishRepository.findById(id).get();
        return dish.getRatingList();
    }

     /*
     * Return a specific rating from a dish
     */
    public DishRating getSpecificDishRating(Long dishid, Long ratingId){
        Dish dish = dishRepository.findById(dishid).get();
        return dish.getSpecificRating(ratingId);
    }

    /*
     * Add a rating to an existing dish object
     */
    public void createDishRating(DishRating dishRating, long dishId){
        Dish ratedDish = dishRepository.findById(dishId).get();
        ratedDish.getRatingList().add(dishRating);
        dishRepository.save(ratedDish);
    }

     /*
     * Update a rating for a dish
     */
    public void updateDishRating(DishRating updatedRating, long dishId, long ratingId){
        Dish updatedDish = dishRepository.findById(dishId).get();
        updatedDish.updateRating(updatedRating, ratingId);
        dishRepository.save(updatedDish);
    }

    /*
     * Deletes rating from list in Dish object
     * Dishid to find the dish that contains the  rating
     * Ratingid is used to search ratings list and remove it
     */
    public void deleteDishRating(long dishId, long ratingId) {
        Dish deletedDish = dishRepository.findById(dishId).get();
        deletedDish.deleteRating(ratingId);
        dishRepository.save(deletedDish);
    }


}
