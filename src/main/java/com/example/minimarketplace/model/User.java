package com.example.minimarketplace.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa, Brunillda Maloku
 * @project mini-marketplace
 * @created 2024-04-01
 */
@Entity
@Table(name ="user")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "user_id",
            updatable = false
    )
    private UUID userId;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private String lastName;

    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private String username;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "varchar(50)" // kanske annat, måste krypteras
    )
    private String password;

    @Column(
            name = "date_of_birth",
            columnDefinition = "date"
    )
    private Date dateOfBirth;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private String email;

    @Column(
            name = "balance",
            nullable = false,
            columnDefinition = "numeric(13, 2)"
    )
    private double balance;

    public User(String firstName, String lastName, String username, String passowrd,
                Date dateOfBirth, String email, double balance){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = passowrd;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.balance = balance;
    }

    public UUID getUserId() {
        return userId;
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

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }
}