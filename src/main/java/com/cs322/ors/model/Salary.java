package com.cs322.ors.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Salary {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@OneToOne(optional = false)
private User user;

private long amount;
private String description;

public Salary() {
}

public Salary(User user, long amount, String description) {
    this.user = user;
    this.amount = amount;
    this.description = description;
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


@Override
public String toString() {
    return "Salary [amount=" + amount + ", description=" + description + ", id=" + id + ", user=" + user + "]";
}





    
}
