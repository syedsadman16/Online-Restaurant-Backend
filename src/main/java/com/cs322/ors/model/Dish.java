package com.cs322.ors.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	@ManyToOne(optional=false)
	@JsonIgnoreProperties({"username", "password", "role", "closed"})
	private User chef;

	// Unidirectional relations
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DishRating> rating;

	
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
		rating = new ArrayList<DishRating>();
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

	public List<DishRating> getRatingList() {
		return rating;
	}

	public DishRating getSpecificRating(Long id) {
		DishRating current = new DishRating();
		for(int i=0; i<rating.size(); i++){
			if(rating.get(i).getId() == id){
			current = rating.get(i);
			}
		}
		return current;
	}

	public void addToRatings(DishRating newRating){
		rating.add(newRating);
	}

	public void updateRating(DishRating newRating){
		for(int i=0; i<rating.size(); i++){
			if(rating.get(i).getId() == id){
				rating.set(i, newRating);
			}
		}
	}

	public void setRatingList(List<DishRating> rating) {
		this.rating = rating;
	}

	public double getAverageRating(){
		double sum = 0;
		for(int i=0; i<rating.size(); i++){
			sum =+ rating.get(i).getRating();
		}
		return sum;
	}

	public void deleteRating(Long id){
		for(int i=0; i<rating.size(); i++){
			if(rating.get(i).getId() == id){
				rating.remove(i);
			}
		}
	}

	@Override
	public String toString() {
		return "Dish [id=" + id + ", chef=" + chef + ", price=" + price + ", description=" + description + ", imageUrl="
				+ imageUrl + ", name=" + name + ", special=" + special + "]";
	}

	
}
