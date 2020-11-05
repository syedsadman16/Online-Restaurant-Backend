package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserWarning")
public class UserWarning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private User customer;

    private String message;

    public UserWarning() {}
    
    public UserWarning(User customer, String message) {
        super();
        this.customer = customer;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

 
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "UserWarning [customer=" + customer + ", id=" + id + ", message=" + message + "]";
    }
    

}
