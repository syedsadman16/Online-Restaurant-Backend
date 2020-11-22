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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; // PK

	@NotBlank(message = "Username is mandatory")
	@Size(min = 1)
	@Column(nullable = false, unique = true)
	private String username;

	@NotBlank(message = "Password is mandatory")
	@Column(nullable = false)
	private String password;

	@NotBlank(message = "Account type is mandatory")
	private String role;
	private boolean closed;
	
	@Column(precision = 13, scale = 2)
	private BigDecimal accountBalance;
	private int rating;

	// Bidirectional Mapping

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<UserWarning> userWarnings = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChefJob> chefJobs = new ArrayList<>();

	@JsonIgnore
	@OneToOne(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private EmployeeInfo employeeInfo;

	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Salary salary;

	@JsonIgnore
	@OneToMany(mappedBy = "userid", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Transaction> transactions = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Dish> dishes = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<DishKeyWord> dishKeywords = new ArrayList<>();

	@JsonIgnore
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private CustomerInfo customerInfo;


	@JsonIgnore
	// Unidirectional
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( cascade = CascadeType.ALL)
	private List<UserRating> ratingList;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( cascade = CascadeType.ALL)
	private List<DeliveryJobs> deliveryJobs;


	public User() {
	}

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.closed = false;

		if (role == "CUSTOMER" || role == "VIP") {
			this.accountBalance = BigDecimal.valueOf(300); // 300 dollars given to customers by default
		}
		
		

		if (role == "CUSTOMER" || role == "VIP" || role == "DELIVERER") {
			ratingList = new ArrayList<>();
			if(ratingList.size() > 0) {
				this.rating = calculateAverageRating();
			} 
		}

		if(role == "DELIVERER"){
			deliveryJobs = new ArrayList<>();
		}

	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public List<UserRating> getRatingList() {
		return this.ratingList;
	}

	public void setRatingList(List<UserRating> rating) {
		this.ratingList = rating;
	}

	public void addToRatings(UserRating uRating){
		ratingList.add(uRating);
	}

	public int getRating(){
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public UserRating getSingleUserRating(Long id) {
		UserRating newRating = new UserRating();
		for(int i=0; i<ratingList.size(); i++){
			if(ratingList.get(i).getId() == id){
			newRating = ratingList.get(i);
			}
		}
		return newRating;
	}

	public void updateRating(UserRating newRating, Long ratingId){
		for(int i=0; i<ratingList.size(); i++){
			if(ratingList.get(i).getId() == ratingId){
				ratingList.set(i, newRating);
			}
		}
	}

	public void deleteRating(Long dishId, Long ratingId){
		for(int i=0; i<ratingList.size(); i++){
			if(ratingList.get(i).getId() == ratingId){
				ratingList.remove(i);
			}
		}
	}

	public int calculateAverageRating(){
		int total = 0;
		for(int i=0; i<ratingList.size(); i++){
			total += ratingList.get(i).getRating();
		}
		return total/ratingList.size();
	}

	public List<DeliveryJobs> getDeliveryJobs() {
		return this.deliveryJobs;
	}

	public void replaceDeliveryJob(DeliveryJobs jobs){
		for(int i=0; i<deliveryJobs.size(); i++){
			if(deliveryJobs.get(i).getId() == jobs.getId()){
				deliveryJobs.set(i, jobs);
			}
		}

	}

	public void setDeliveryJob(List<DeliveryJobs> deliveryJobs) {
		this.deliveryJobs = deliveryJobs;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", closed="
				+ closed + "]";
	}

}