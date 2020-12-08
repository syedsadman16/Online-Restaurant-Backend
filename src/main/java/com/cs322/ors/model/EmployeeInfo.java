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
    private int demotions;
    private int promotions;
    
	public EmployeeInfo() {
	}

    public EmployeeInfo(String address, String name, User user) {
        this.user = user;
        this.address = address;
        this.name = name;
        this.demotions = 0;
        this.promotions = 0;

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

    public int getPromotions() {
        return promotions;
    }

    public int getDemotions() {
        return demotions;
    }
    
    public void incrementDemotions() {
        this.demotions++;
    }
    
    public void incrementPromotions() {
        this.promotions++;
    }
    
    public void decrementDemotions() {
        this.demotions--;
    }

    public void decrementPromotions() {
        this.promotions--;
    }

	@Override
	public String toString() {
		return "EmployeeInfo [id=" + id + ", user=" + user + ", name=" + name + ", address=" + address + ", fired="
				+ fired + ", demotions=" + demotions + ", promotions=" + promotions + "]";
	}
    


    
}