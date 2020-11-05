package com.cs322.ors.model;


import javax.persistence.*;

@Entity
public class DeliveryJobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne(optional = false)
    private Order order;

	public DeliveryJobs() {}
	
    public DeliveryJobs(Order order) {
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
