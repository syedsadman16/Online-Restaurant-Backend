package com.cs322.ors.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(optional=false)
	@JsonIgnore
	private User chef;
	
	@Column(precision = 13, scale = 2)
	private BigDecimal price;
	private String description;
	private String imageUrl;
	private String name;
	private boolean special;
	
	public Dish() {
		
	}
	
	public Dish(String name, User chef, String description, String imageUrl, BigDecimal price, boolean special) {
		super();
		this.chef = chef;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
		this.name = name;
		this.special = special;
	}


	public boolean isSpecial() {
		return special;
	}


	public void setSpecial(boolean special) {
		this.special = special;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Dish [id=" + id + ", chef=" + chef + ", price=" + price + ", description=" + description + ", imageUrl="
				+ imageUrl + ", name=" + name + ", special=" + special + "]";
	}

	
}
