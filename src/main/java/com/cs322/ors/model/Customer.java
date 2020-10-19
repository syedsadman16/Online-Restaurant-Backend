package com.cs322.ors.model;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @OrderBy
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private double balance;
    private String email;
    private String firstName;
    private String lastName;
    private int phoneNumber;



    public Customer() { }

    public Customer(long id, String firstName, String lastName, String email, int phoneNumber, double balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    


    
}
