package com.example.minimarketplace.model;

import jakarta.persistence.*;

/**
 * @author Tiffany Dizdar
 */
@Entity
@Table(name = "order_content")
public class OrderContent {

    //TODO: not finished, not sure how to map relationship yet
    @JoinColumn(
            name = "order_id",
            nullable = false,
            updatable = false
    )
    private long orderID;

    //TODO: add productID


}
