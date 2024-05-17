package com.example.minimarketplace.model.communication.response.user;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-16
 */
public class UserResponse {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private double balance;

    public UserResponse(String firstName, String lastName, String username, String email, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }
}