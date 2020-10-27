package com.cs322.ors.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class DeliveryJobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<Order> orderId;
//    @ManyToMany
//    private CustomerInfo customerInfoId;


    public DeliveryJobs(long id, List<Order> orderId) {
        this.id = id;
        this.orderId = orderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Order> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Order> orderId) {
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
