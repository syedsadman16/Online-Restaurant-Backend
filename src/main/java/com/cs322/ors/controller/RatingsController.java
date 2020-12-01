package com.cs322.ors.controller;

import com.cs322.ors.model.*;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingsController {

    @Autowired
    DishRatingService dishRatingService;

    @Autowired
    UserRatingsService userRatingsService;

    @Autowired
    ChefStatusService chefStatusService;


     //------------------------------------User Ratings----------------------------------------------------

    @GetMapping("/users")
    @PreAuthorize("hasRole('MANAGER')")
    public List<UserRatings> getAllUserRatings() {
        return userRatingsService.getAllUserRatings();
    }

    @GetMapping("/users/{personId}")
    @PreAuthorize("hasRole('MANAGER')")
    public List<UserRatings> getPersonsUserRatings(@PathVariable Long personId) {
        return userRatingsService.getAllUserRatingsByPerson(personId);
    }

    @GetMapping("/users/me")
    @PreAuthorize("isAuthenticated()")
    public List<UserRatings> getMyUserRatings(Authentication authUser) {
        User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
        return userRatingsService.getAllUserRatingsByPerson(currentUser.getId());
    }

    @GetMapping("/users/getAverage/{personId}")
    @PreAuthorize("hasRole('MANAGER')")
    public double getAverageUserRatings(@PathVariable Long personId) {
        return userRatingsService.calculateAverageRatings(personId);
    }

    @PostMapping("/users/add")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','DELIVERER', 'MANAGER')")
    public void addNewUserRatings(@RequestBody UserRatings userRatings) {
        userRatingsService.addUserRatings(userRatings);
    }

    @PostMapping("/users/update")
    @PreAuthorize("hasRole('MANAGER')")
    public void updateUserRatings(@RequestBody UserRatings updatedUserRatings) {
        userRatingsService.updateUserRatings(updatedUserRatings);
    }

    @DeleteMapping("/users/{ratingsId}")
    @PreAuthorize("hasRole('MANAGER')")
    public void deleteUserRatings(@PathVariable Long ratingsId) {
        userRatingsService.deleteUserRatings(ratingsId);
    }



    
    //------------------------------------Dish Ratings----------------------------------------------------
    

    /*
     * Controller mappings for Dish Ratings
     * Retrieve all dish objects with or without ratings
     */
    @RequestMapping("/dishes")
    public List<Dish> getAllDishes(){
        return dishRatingService.getAllDishes();
    }

     /*
     * All ratings from a dish
     */
    @RequestMapping("/dishes/{dishId}")
    public List<DishRating> getOneDishRatings(@PathVariable Long dishId){
        return dishRatingService.getDishRatings(dishId);
    }

     /*
     * Specific rating from a dish
     */
    @RequestMapping("/dishes/{dishId}/{ratingId}")
    public DishRating getSpecificDishRatings(@PathVariable Long dishId, @PathVariable Long ratingId){
        return dishRatingService.getSpecificDishRating(dishId, ratingId);
    }


     /*
     * Create rating for dish
     */ 
    @PostMapping("/dishes/{dishId}/create")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','MANAGER')")
    public void createDishRating(@RequestBody DishRating dishRating, @PathVariable Long dishId){
        dishRatingService.createDishRating(dishRating, dishId);
    }

     /*
     * Update rating for Dish
     */
    @PostMapping("/dishes/{dishId}/update/{ratingId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','MANAGER')")
    public void updateDishRating(@RequestBody DishRating dishRating, @PathVariable Long dishId, @PathVariable Long ratingId){
        dishRatingService.updateDishRating(dishRating, dishId, ratingId);
    }

     /*
     * Remove rating for dish
     */
    @DeleteMapping("/dishes/{dishId}/{ratingId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP', 'MANAGER')")
    public void deleteDishRating(@PathVariable Long dishId,  @PathVariable Long ratingId){
        dishRatingService.deleteDishRating(dishId, ratingId);
    }



}
