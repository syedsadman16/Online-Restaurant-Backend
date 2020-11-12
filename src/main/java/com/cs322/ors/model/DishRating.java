package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.cs322.ors.security.UserPrincipal;

@Entity
public class DishRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private User critic;
    private double rating;
    private String comments = "";

	public DishRating() {
	}

    public DishRating(double rating, User critic) {
        this.rating = rating;
        this.critic = critic;
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

}
