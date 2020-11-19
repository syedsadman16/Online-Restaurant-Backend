package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CustomerInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	private String address;
	
	//This name is different from Username of the user model... Could be the customer's real name
	private String name;
	
	
	@OneToOne (optional = false)  //each user has their own unique information
	private User customer;			  //also field cannot be left blank(its needed to create customerInfo for that user)

	
	//empty constructor for easier initialization
	public CustomerInfo() {
		super();
	}

	public CustomerInfo(String address, String name, User customer) {
		super();
		this.address = address;
		this.name = name;
		this.customer = customer;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}
	
	
}
