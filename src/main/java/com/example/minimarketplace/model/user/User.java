package com.example.minimarketplace.model.user;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa, Brunillda Maloku
 * @project mini-marketplace
 * @created 2024-04-01
 */
@Entity
@Table(name ="webuser")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator" //deprecated, find alternative (still works)
    )
    @Column(
            name = "user_id",
            updatable = false,
            nullable = false
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

    public User(String firstName, String lastName, String username, String password,
                Date dateOfBirth, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.balance = 2000.00;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}    //default constructor workaround

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