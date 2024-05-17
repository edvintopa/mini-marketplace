package com.example.minimarketplace.model.order;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name ="weborder")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator" //deprecated, find alternative (still works)
    )
    @Column(
            name = "order_id",
            updatable = false,
            nullable = false
    )
    private UUID orderId;

    @Column(
            name = "seller",
            updatable = false,
            nullable = false
    )
    private UUID sellerId;

    @Column(
            name = "buyer",
            updatable = false,
            nullable = false
    )
    private UUID buyerId;

    @Column(
            name = "total",
            columnDefinition = "numeric(13, 2)",
            nullable = false
    )
    private double total;

    @Column(
            name = "order_date",
            columnDefinition = "date",
            nullable = false
    )
    private Date orderDate;

    @Column(
            name = "is_confirmed",
            nullable = false
    )
    private boolean isConfirmed;

    @Column(
            name = "product_id",
            updatable = false,
            nullable = false
    )
    private UUID productId;

    public Order(UUID sellerId,
                 UUID buyerId,
                 double total,
                 UUID productId) {

        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.total = total;
        this.orderDate = new Date();
        this.isConfirmed = false;
        this.productId = productId;
    }

    public Order() {

    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public UUID getBuyerId() {
        return buyerId;
    }

    public double getTotal() {
        return total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public UUID getProductId() {
        return productId;
    }
}