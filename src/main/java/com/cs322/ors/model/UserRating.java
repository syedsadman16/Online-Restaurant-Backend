package com.cs322.ors.model;

import javax.persistence.*;
import java.util.List;

public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int rating; // 1-5 star rating
    @OneToMany
    private List<Order> orderId;

    public UserRating(long id, int rating, List<Order> orderId) {
        this.id = id;
        this.rating = rating;
        this.orderId = orderId;
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

    public List<Order> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Order> orderId) {
        this.orderId = orderId;
    }
}
