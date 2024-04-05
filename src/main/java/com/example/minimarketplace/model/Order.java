package com.example.minimarketplace.model;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name ="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderID;
    @Column(name = "buyer")
    private UUID buyer;
    @Column(name = "passwordHash")
    private String passwordHash;
    @Column(name = "cost")
    private double cost;

    @Column(name = "orderdate")
    private String orderDate;


    public UUID getOrderID() {
        return orderID;
    }

    public UUID getBuyer() {
        return buyer;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public double getCost() {
        return cost;
    }

    public String getOrderDate() {
        return orderDate;
    }
}