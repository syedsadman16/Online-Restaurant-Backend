package com.cs322.ors.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "`ORDER`")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	// @JsonIgnoreProperties({"password", "role","closed"})
	@JoinColumn(name = "customer_id")
	@JsonIdentityReference(alwaysAsId = true)
	private User customer;

	@Column(columnDefinition = "DATE")
	private LocalDateTime date;

	private int type; // 0 = pick-up, 1 = delivery, 2 = reservation
	private boolean completed;
	private boolean cancelled;

	// Bidirectional Mapping

	@JsonIgnore
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private ChefJob chefJob;

	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<DishOrder> dishOrders = new ArrayList<>();

	@JsonIgnore
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private DeliveryStatus deliveryStatus;

	public Order() {
	}

	public Order(User customer, int type) {
		super();
		this.customer = customer;
		this.type = type;
		this.date = LocalDateTime.now();
		this.completed = false;
		this.cancelled = false;
	}
	
	

	public List<DishOrder> getDishOrders() {
		return dishOrders;
	}

	public void setDishOrders(List<DishOrder> dishOrders) {
		this.dishOrders = dishOrders;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean getCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", date=" + date + ", type=" + type + ", completed="
				+ completed + ", cancelled=" + cancelled + "]";
	}

}
