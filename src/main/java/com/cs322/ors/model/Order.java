package com.cs322.ors.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="`ORDER`")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private User customer;	
	
	@Column(columnDefinition = "DATE")
	private LocalDateTime date;
	
	private int type; // 0 = pick-up, 1 = delivery, 2 = reservation
	private boolean completed;
	private boolean cancelled;

	public Order() {}
	
	public Order(User customer, int type) {
		super();
		this.customer = customer;		
		this.type = type;
		this.date = LocalDateTime.now();
		this.completed = false;
		this.cancelled = false;
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
