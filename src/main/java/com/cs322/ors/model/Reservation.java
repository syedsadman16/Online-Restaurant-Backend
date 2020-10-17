package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name  = "Dish")
public class Reservation{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int orderId; //PK

    private String time;
    private int table_num;

    public Reservation() {
    }

    public Reservation(int orderId, String time, int table_num) {
        this.orderId = orderId;
        this.time = time;
        this.table_num = table_num;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTable_num() {
        return table_num;
    }

    public void setTable_num(int table_num) {
        this.table_num = table_num;
    }

    @Override
    public String toString() {
        return "Reservation [orderId=" + orderId + ", table_num=" + table_num + ", time=" + time + "]";
    }

    

}
