package com.cs322.ors.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Salary {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

private long amount;
private String description;

@OneToOne(optional = false)
private User user;


public Salary() {
}

public Salary(long amount, String description, User user) {
    this.amount = amount;
    this.description = description;
    this.user = user;
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
