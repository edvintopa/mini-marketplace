package com.example.minimarketplace.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name ="order")
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    @Column(
            name = "order_id",
            updatable = false
    )
    private UUID orderID;

    @ManyToOne
    @JoinColumn(
            name = "seller",
            referencedColumnName = "user_id",
            nullable = false
    )
    private User seller;

    @ManyToOne
    @JoinColumn(
            name = "buyer",
            referencedColumnName = "user_id",
            nullable = false
    )
    private User buyer;

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

    @Enumerated(EnumType.STRING)
    @Column(
            name = "order_status",
            nullable = false
    )
    private OrderStatus orderStatus;

    public Order(User seller, User buyer, double total,
                 Date orderDate, OrderStatus orderStatus){
        this.seller = seller;
        this.buyer = buyer;
        this.total = total;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public User getSeller() {
        return seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public double getTotal() {
        return total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}