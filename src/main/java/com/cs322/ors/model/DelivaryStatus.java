package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DelivaryStatus {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne (optional = false)  //each order can have a unique delivery status
	private Order order;			// only order of type 1 should be up for delivery
	
	private boolean isDelivered;
	private boolean isRecieved;
	
	
	public DelivaryStatus() {
		
	}
	
	public DelivaryStatus(long id, Order order, boolean isDelivered, boolean isRecieved) {
		super();
		this.id = id;
		this.order = order;
		this.isDelivered = isDelivered;
		this.isRecieved = isRecieved;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public boolean isRecieved() {
		return isRecieved;
	}

	public void setRecieved(boolean isRecieved) {
		this.isRecieved = isRecieved;
	}
	
	
	
	
}
