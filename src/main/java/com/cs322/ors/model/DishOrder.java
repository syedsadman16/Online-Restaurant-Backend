package com.cs322.ors.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * Join Table using ManyToOne relationships
 */
@Entity
public class DishOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(optional = false)
	private Dish dish;	
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int quantity;
	
	
	public DishOrder() {
	}

	public DishOrder(Dish dish, Order order, int quantity) {
		super();
		this.dish = dish;
		this.order = order;
		this.quantity = quantity;
	}

	public DishOrder(Dish dish, int quantity) {
		super();
		this.dish = dish;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "DishOrder [id=" + id + ", dish=" + dish + ", order=" + order + ", quantity=" + quantity + "]";
	}
	

}
