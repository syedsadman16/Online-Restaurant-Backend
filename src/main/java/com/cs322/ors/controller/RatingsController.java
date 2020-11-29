package com.cs322.ors.controller;

import com.cs322.ors.model.Dish;
import com.cs322.ors.model.DishRating;
import com.cs322.ors.model.User;
import com.cs322.ors.model.UserRating;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.ChefStatusService;
import com.cs322.ors.service.DishRatingService;
import com.cs322.ors.service.UserRatingService;
import com.cs322.ors.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingsController {

    @Autowired
    UserRatingService userRatingService;
    
    @Autowired
    DishRatingService dishRatingService;
    
    @Autowired
    ChefStatusService chefStatusService;
    
    @Autowired
    UserService	userService;

     //------------------------------------User Ratings----------------------------------------------------

    /*
     * Controller mappings for User Ratings
     * Only the manager can view all ratings for all users
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('MANAGER')")
    public List<User> getAllUsersRating(){
        return userRatingService.getAllUserRatings();
    }
    
    @GetMapping("/chef/{id}")
    @PreAuthorize("hasRole('MANAGER','CHEF')")
    public double getAverageChefRating(@PathVariable long id) {
    	return chefStatusService.averageRating(userService.getUserById(id).get()).get(0);
    }
    /*
     * Let current user get their own ratings
     */ 
    @GetMapping("/users/myRatings")
    @PreAuthorize("isAuthenticated()")
    public List<UserRating> getPersonalRatings(Authentication authUser){
        User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
        return userRatingService.getUsersRatings(currentUser.getId());
    }

    /*
     * Let the manager view ratings for a single user
     */
    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('MANAGER')")
    public List<UserRating> getSpecificUsersRatings(@PathVariable Long userId){
        return userRatingService.getUsersRatings(userId);
    }

    /*
     * Manager can get a specific rating 
     */
    @GetMapping("/users/{userId}/{ratingId}")
    @PreAuthorize("hasRole('MANAGER')")
    public UserRating getSpecificUsersRatings(@PathVariable Long userId, @PathVariable Long ratingId){
        return userRatingService.getUserSingleRatings(userId, ratingId);
    }


    /*
     * Customers and Delivery can create a rating
     * @userId is user being rated
     */
    @PostMapping("/users/create/{userId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','DELIVERER')")
    public void createUserRating(@RequestBody UserRating userRating, @PathVariable Long userId, Authentication authUser ){
        User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
        userRatingService.postUserRating(userId, userRating, currentUser );
    }

     /*
     * Registered users and Manager can update ratings
     */
    @PostMapping("/users/update/{userId}/{ratingId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','DELIVERER', 'MANAGER')")
    public void updateUserRating(@RequestBody UserRating userRating, @PathVariable Long userId, @PathVariable Long ratingId){
        userRatingService.editUserRating(userRating, userId, ratingId);
    }

    /*
     * Registered users and Manager can delete ratings
     */
    @PostMapping("/users/delete")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','DELIVERER', 'MANAGER')")
    public void deleteUserRating(@PathVariable Long id, @PathVariable Long ratingId){
        userRatingService.deleteUserRating(id, ratingId);
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
    @DeleteMapping("/dishes/{id}/delete/{dishId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP', 'MANAGER')")
    public void deleteDishRating(@PathVariable Long id,  @PathVariable Long dishId){
        dishRatingService.deleteDishRating(id, dishId);
    }



}
