package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.websocket.server.ServerEndpoint;

@Entity
public class Dish {

    @Id
    private int dishId; 
    private int price;
    private String title;
    private String description;
    private String chefName;
    private String chefID;
    private String imageUrl;

    public Dish() { }

    public Dish(int dishId, int price, String title, String description, String chefName, String chefID, String imageUrl) {
        this.dishId = dishId;
        this.price = price;
        this.title = title;
        this.description = description;
        this.chefName = chefName;
        this.chefID = chefID;
        this.imageUrl = imageUrl;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public String getChefID() {
        return chefID;
    }

    public void setChefID(String chefID) {
        this.chefID = chefID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
