package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DishRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int rating; // 1-5 star rating
    
    @OneToOne(optional = false)
    private Order order;

    public DishRating(int rating, Order order) {
        this.rating = rating;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
