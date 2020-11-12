package com.cs322.ors.model;

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
	private float accountBalance;

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

	// @JsonIgnore
	// @OneToOne(mappedBy = "critic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	// private UserRating userRatingByCritic;

	// @JsonIgnore
	// @OneToOne(mappedBy = "victim", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	// private UserRating userRatingByVictim;

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

//
//	// Unidirectional
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<UserRating> rating;



	public User() {
	}

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.closed = false;

		if (role == "CUSTOMER" || role == "VIP") {
			this.accountBalance = 300; // 300 dollars given to customers by default
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

//	public List<UserRating> getRating() {
//		return this.rating;
//	}
//
//	public void setRating(List<UserRating> rating) {
//		this.rating = rating;
//	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", closed="
				+ closed + "]";
	}

}