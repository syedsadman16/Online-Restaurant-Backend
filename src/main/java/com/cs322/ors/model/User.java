package com.cs322.ors.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id; //PK

    @Column(nullable = false)
	private String username;
	
	@Column(nullable = false,  unique=true)
	private String password;
	
	private String roles;	
	private String permissions;
	private int enable; //Account closed (user can't login)
    private int banned; // Account banned (user can login)

    public User(){}

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
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

	public List<String> getRoleList(){
		if(roles.length() > 0) {
			return Arrays.asList(roles.split(","));
		}
		return new ArrayList<>();
	}
	
	public List<String> getPermissionList(){
		if(permissions.length() > 0) {
			return Arrays.asList(permissions.split(","));
		}
		return new ArrayList<>();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ ", permissions=" + permissions + ", enable=" + enable + ", banned=" + banned + "]";
	}


}