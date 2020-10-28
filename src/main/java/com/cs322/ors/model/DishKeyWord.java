package com.cs322.ors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KeyWords")
public class DishKeyWord {
	
	@Id
	@GeneratedValue  //auto by default
	private long Id;
	
	@Column(name = "Word")
	private String keyWord;
	
	@ManyToOne //multiple dishes can be marked by same keyword	
	private Dish dish;
	
	// I didn't relate this class to chef users because chefID did not feel needed in the context. 
	// we just need to relate a dish to a category/keyword

	
	//empty constructor
	public DishKeyWord() {

	}
	
	// main constructor
	public DishKeyWord(long id, String keyWord, Dish dish) {
		super();
		Id = id;
		this.keyWord = keyWord;
		this.dish = dish;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	
	
	
}
