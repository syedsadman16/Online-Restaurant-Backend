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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
	private boolean verified;


	// Bidirectional Mapping

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Discussion> discussions = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<UserWarning> userWarnings = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ChefJob> chefJobs = new ArrayList<>();

	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<DishKeyWord> dishKeywords = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "critic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<DishRating> dishRating = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private CustomerInfo customerInfo;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Reservation> reservation;
	
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<UserRatings> userRatings;

	@JsonIgnore
	@OneToMany(mappedBy = "victim", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Claims> claims;

	//@JsonIgnore
	@OneToMany( cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DeliveryJobs> deliveryJobs;


	public User() { }

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.closed = false;
		this.verified = role == "MANAGER" ? true : false;

		if(role == "DELIVERER"){
			deliveryJobs = new ArrayList<>();
		}
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

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
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
	

	public List<Order> getOrders() {
		return orders;
	}

	public void setDeliveryJob(List<DeliveryJobs> deliveryJobs) {
		this.deliveryJobs = deliveryJobs;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setChefJobs(List<ChefJob> chefJobs) {
		this.chefJobs = chefJobs;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public void setDishKeywords(List<DishKeyWord> dishKeywords) {
		this.dishKeywords = dishKeywords;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public void setDeliveryJobs(List<DeliveryJobs> deliveryJobs) {
		this.deliveryJobs = deliveryJobs;
	}

	
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", closed="
				+ closed + "]";
	}

}