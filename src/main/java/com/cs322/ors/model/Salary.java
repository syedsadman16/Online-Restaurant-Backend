package com.cs322.ors.model;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Salary {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
@JsonIdentityReference(alwaysAsId = true)
@OneToOne(optional = false)
private User user;

private BigDecimal amount;

public Salary() {
}

public Salary(User user, BigDecimal amount) {
    this.user = user;
    this.amount = amount;
}


public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}

public BigDecimal getAmount() {
    return amount;
}

public void setAmount(BigDecimal amount) {
    this.amount = amount;
}

@Override
public String toString() {
	return "Salary [id=" + id + ", user=" + user + ", amount=" + amount + "]";
}









    
}
