package com.cs322.ors.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id; //PK

    @Column(nullable = false)
	private String username;
	
	@Column(nullable = false,  unique=true)
	private String password;
	
	private String role;	
	private int enable; //Account closed (user can't login)
    private int banned; // Account banned (user can login)

    public User(){}

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.enable = 1;
		this.banned = 0;
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

	public void setrole(String role) {
		this.role = role;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getBanned() {
		return banned;
	}

	public void setBanned(int banned) {
		this.banned = banned;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", enable="
				+ enable + ", banned=" + banned + "]";
	}


}