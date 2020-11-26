package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.cs322.ors.security.UserPrincipal;

@Entity
public class DishRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User critic;
    
    @ManyToOne
    private Dish dish;
    
    private double rating;
    private String comments = "";

	public DishRating() {
	}

    public DishRating(double rating, User critic, Dish dish) {
        this.rating = rating;
        this.critic = critic;
        this.dish = dish;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getCritic() {
        return this.critic;
    }

    public void setCritic(User critic) {
        this.critic = critic;
    }

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}
    

}
