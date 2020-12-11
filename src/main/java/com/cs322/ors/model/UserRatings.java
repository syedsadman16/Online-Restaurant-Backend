package com.cs322.ors.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class UserRatings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    /*
     * This is the user that is being rated
     */
    @ManyToOne
    @JoinColumn(name="person_id")
    private User person;

    /*
     * Save the order just for reference
     */
    @OneToOne
    @JoinColumn(name="order_id")
    private Order order;

    @OneToOne(mappedBy = "userRatings", cascade = CascadeType.ALL  ,orphanRemoval = true, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Claims claims;

    private int type; // 0 is default, 1 is dismissed
    private double rating;
    private String comments = "";

    public UserRatings() {

    }

    public UserRatings(double rating, String comments, User person, Order order, int type) {
        this.person = person;
        this.rating = rating;
        this.comments = comments;
        this.order = order;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
