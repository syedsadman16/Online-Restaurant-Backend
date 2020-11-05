package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne(optional = false)
    private User userid;
    
    private long amount;
    private String description;
    

    public Transaction() {}

    public Transaction(User userid, long amount, String description) {
        this.userid = userid;
        this.amount = amount;
        this.description = description;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", description=" + description + ", id=" + id + ", userid=" + userid
                + "]";
    }
    

}
