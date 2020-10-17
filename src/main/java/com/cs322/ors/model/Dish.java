package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name  = "Dish")
public class Dish{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String name; //PK

    private String description;
    private double price;

    
    public Dish(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish [description=" + description + ", name=" + name + ", price=" + price + "]";
    }

    
}
