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

    /*
     * Contains a reference to Dish and Order classes
     * Dish contains User chef, Order contains User customer
     */
    @OneToOne(optional = false)
    private DishOrder dishOrder;

    /*
     *  Value ranging from 1-5 stars
     */
    private int rating;

	public DishRating() {
	}

    public DishRating(int rating, DishOrder dishOrder) {
        this.rating = rating;
        this.dishOrder = dishOrder;
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
