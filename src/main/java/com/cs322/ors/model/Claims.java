package com.cs322.ors.model;

import javax.persistence.*;

import antlr.collections.List;

/*
 * Purpose is to dispute claims made by registered users
 * Manager can view claims made by deliverers and customers for 
 * poor ratings and decidie to demote or dismiss 
 */
@Entity
public class Claims {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @OneToOne
    UserRating userRating;
    String message;

    public Claims() {}

    public Claims( UserRating userRating, User plantiff, String message) {
        this.userRating = userRating;

        this.message = message;
    }

    public long getId() {
        return this.Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public UserRating getUserRating() {
        return this.userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
}
