package com.cs322.ors.model;


import javax.persistence.*;

@Entity
public class DeliveryJobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private Order orderId;
//    @ManyToMany
//    private CustomerInfo customerInfoId;


    public DeliveryJobs(long id, Order orderId) {
        this.id = id;
        this.orderId = orderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

//    public CustomerInfo getCustomerInfoId() {
//        return customerInfoId;
//    }
//
//    public void setCustomerInfoId(CustomerInfo customerInfoId) {
//        this.customerInfoId = customerInfoId;
//    }
}
