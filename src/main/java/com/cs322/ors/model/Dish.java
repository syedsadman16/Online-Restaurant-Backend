package com.cs322.ors.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(optional=false)
	private User chef;
	
	@Column(precision = 13, scale = 2)
	private BigDecimal price;
	private String description;
	private String imageUrl;
	private String name;
	
	
	public Dish(User chef, String description, String imageUrl, BigDecimal price, String name) {
		super();
		this.chef = chef;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
		this.name = name;
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
		return "Dish [id=" + id + ", chef=" + chef + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", price=" + price + ", name=" + name + "]";
	}

	
}
