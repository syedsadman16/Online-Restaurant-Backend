package com.cs322.ors.controller;

import com.cs322.ors.model.Dish;
import com.cs322.ors.model.DishRating;
import com.cs322.ors.model.UserRating;
import com.cs322.ors.service.DishRatingService;
import com.cs322.ors.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingsController {

    @Autowired
    UserRatingService userRatingService;
    @Autowired
    DishRatingService dishRatingService;

    /*
     * Controller mappings for User Ratings
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('MANAGER')")
    public List<UserRating> getAllUsersRating(){
        return userRatingService.getAllUserRatings();
    }

    @PostMapping("/users/create")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','DELIVERER')")
    public void createUserRating(@RequestBody UserRating userRating){
        userRatingService.postUserRating(userRating);
    }

    @PostMapping("/users/update")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','DELIVERER', 'MANAGER')")
    public void updateUserRating(@RequestBody UserRating userRating){
        userRatingService.editUserRating(userRating);
    }

    @PostMapping("/users/delete")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','DELIVERER', 'MANAGER')")
    public void deleteUserRating(@PathVariable Long id){
        userRatingService.deleteUserRating(id);
    }


    /*
     * Controller mappings for Dish Ratings
     */
    @RequestMapping("/dishes")
    public List<Dish> getAllDishes(){
        return dishRatingService.getAllDishes();
    }

    @RequestMapping("/dishes/{dishId}")
    public List<DishRating> getOneDishRatings(@PathVariable Long dishId){
        return dishRatingService.getDishRatings(dishId);
    }

    @PostMapping("/dishes/{dishId}/create")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','MANAGER')")
    public void createDishRating(@RequestBody DishRating dishRating, @PathVariable Long dishId){
        dishRatingService.createDishRating(dishRating, dishId);
    }

    @PostMapping("/dishes/{dishId}/update")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','MANAGER')")
    public void updateDishRating(@RequestBody DishRating dishRating, @PathVariable Long dishId){
        dishRatingService.updateDishRating(dishRating, dishId);
    }

    @PostMapping("/dishes/{id}/delete/{dishId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP', 'MANAGER')")
    public void deleteDishRating(@PathVariable Long id,  @PathVariable Long dishId){
        dishRatingService.deleteDishRating(id, dishId);
    }



}
