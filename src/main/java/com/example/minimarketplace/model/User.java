package com.example.minimarketplace.model;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-01
 */
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(name = "username")
    private String username;
    @Column(name = "passwordHash")
    private String passwordHash;
    @Column(name = "balance")
    private double balance;
}
