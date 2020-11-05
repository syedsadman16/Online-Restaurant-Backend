package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ChefJob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(optional = false)
	private User chef;	
	
	@OneToOne(optional = false)
	private Order order;	
	private boolean completed;
	
	public ChefJob() {}
	
	
	public ChefJob(User chef, Order order) {
		super();
		this.chef = chef;
		this.order = order;
		this.completed = false;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getChef() {
		return chef;
	}
	public void setChef(User chef) {
		this.chef = chef;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	

}
