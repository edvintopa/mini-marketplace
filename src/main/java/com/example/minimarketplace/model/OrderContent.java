package com.example.minimarketplace.model;

import com.example.minimarketplace.model.product.Product;
import jakarta.persistence.*;

/**
 * @author Tiffany Dizdar
 */
@Entity
@Table(name = "order_content")
public class OrderContent {

    @Id //composite PK
    @ManyToOne //one order, many instances
    @JoinColumn(
            name = "order_id",
            nullable = false
    )
    private Order order;

    @Id //composite PK
    @ManyToOne //one product, many instances
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    private Product product;

}
