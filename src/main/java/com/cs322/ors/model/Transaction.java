package com.cs322.ors.model;

import java.math.BigDecimal;

import javax.persistence.Column;
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
    
    @Column(precision = 13, scale = 2)
    private BigDecimal amount;
    private int type; // 0 = negative amount, 1 = positive amount
    private String description;
    

    public Transaction() {}

    public Transaction(User userid, BigDecimal amount, String description, int type) {
        this.userid = userid;
        this.amount = amount;
        this.description = description;
    }
    

    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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
