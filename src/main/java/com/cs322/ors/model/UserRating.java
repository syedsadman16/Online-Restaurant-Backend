package com.cs322.ors.model;

import javax.persistence.*;
import java.util.List;

/*
 * Have a table that contains a users ratings - seperate from orders
 * Customers can rate delivery person and vice versa
 * (Can be used to show current user who they rated and show manager
 * good/bad ratings of a victim)
 */
@Entity
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rating;

    /*
     * Keep track of the User who submitted the rating
     * When list of ratings is viewed for current User, it will
     * display the critic - only the critic and manager can 
     * update the rating 
     */
    @OneToOne
    private User critic;

    /*
     * Keep track of the order that rating was made for
     */
    @OneToOne
    private DishOrder dishOrder;


    public UserRating() {
      
    }

    public UserRating(int rating, User critic, DishOrder order) {
        //this.id = id;
        this.rating = rating;
        this.dishOrder = order;
        this.critic = critic;
    }


    public DishOrder getDishOrder() {
        return dishOrder;
    }

    public void setDishOrder(DishOrder dishOrder) {
        this.dishOrder = dishOrder;
    }

    public User getCritic() {
        return critic;
    }

    public void setCritic(User critic) {
        this.critic = critic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public DishOrder getOrder() {
        return dishOrder;
    }

    public void setOrder(DishOrder order) {
        this.dishOrder = order;
    }
}
