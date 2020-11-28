package com.cs322.ors.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;


@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public Reservation() {}
	
	@Embedded
	private TimeSlot timeSlot;

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	
    @ManyToOne( optional = false)
    private User customer;

    @ManyToOne(optional = false)
    private RestaurantTable table;

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public RestaurantTable getTable() {
		return table;
	}

	public void setTable(RestaurantTable table) {
		this.table = table;
	}

	public Reservation(TimeSlot timeSlot, User customer, RestaurantTable table) {
		super();
		this.timeSlot = timeSlot;
		this.customer = customer;
		this.table = table;
	}
	
	
	
	
}