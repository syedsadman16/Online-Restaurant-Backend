package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EmployeeInfo{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne(optional = false)
    private User user;
    private String name;
    private String address;

    private boolean fired;
    private boolean demotion;
    
	public EmployeeInfo() {
	}

    public EmployeeInfo(String address, String name, User user) {
        this.user = user;
        this.address = address;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public boolean isDemotion() {
        return demotion;
    }

    public void setDemotion(boolean demotion) {
        this.demotion = demotion;
    }

    @Override
    public String toString() {
        return "EmployeeInfo [demotion=" + demotion + ", fired=" + fired + ", id=" + id + ", user=" + user + "]";
    }

    
}