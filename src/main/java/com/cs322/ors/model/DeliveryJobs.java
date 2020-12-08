package com.cs322.ors.model;


import javax.persistence.*;

@Entity
public class DeliveryJobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne(optional = false)
    private Order order;

    /*
     * 0 - Job is available for delivery
     * 1 - Job is being delivered
     */
    private int status = 0;


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

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
