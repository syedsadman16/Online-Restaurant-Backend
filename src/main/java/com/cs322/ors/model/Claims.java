package com.cs322.ors.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
//
//    @OneToOne
//    UserRatings userRatings;

    @OneToOne
    User victim;

    String message;

    public Claims() {}

    public Claims( UserRatings userRating, User victim, String message) {
        //this.userRatings = userRating;
        this.victim = victim;
        this.message = message;
    }

    public long getId() {
        return this.Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

//    public UserRatings getUserRating() {
//       // return this.userRatings;
//    }
//
//    public void setUserRating(UserRatings userRating) {
//       // this.userRatings = userRating;
//    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getVictim() {
        return victim;
    }

    public void setVictim(User victim) {
        this.victim = victim;
    }
}
