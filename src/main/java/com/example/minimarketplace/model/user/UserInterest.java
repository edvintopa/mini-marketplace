package com.example.minimarketplace.model.user;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
@Entity
@Table(name = "user_interest")
public class UserInterest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(
            name = "interest_id",
            updatable = false,
            nullable = false
    )
    private UUID interestId;

    @Column(
            name = "user_id",
            nullable = false
    )
    private UUID userId;

    @Column(
            name = "product_type",
            nullable = false,
            columnDefinition = "varchar(100)"
    )
    private String interest;

    public UUID getInterestId() {
        return interestId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getInterest() {
        return interest;
    }
}
