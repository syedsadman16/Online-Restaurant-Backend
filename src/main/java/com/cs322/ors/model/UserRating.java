package com.cs322.ors.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int rating; // 1-5 star rating

    @OneToOne
    private Order order;
    

    public UserRating() {
      
    }

    public UserRating(long id, int rating, Order order) {
        this.id = id;
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
