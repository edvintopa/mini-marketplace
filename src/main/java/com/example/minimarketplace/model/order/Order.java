package com.example.minimarketplace.model.order;

import com.example.minimarketplace.model.user.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name ="weborder")
public class Order {

    @Id
    @SequenceGenerator( //sequence to increment order id
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
            nullable = false,
            updatable = false
    )
    private UUID orderID;

    @ManyToOne //one seller, many orders
    @JoinColumn(
            name = "seller", //name in order table
            referencedColumnName = "user_id", //name in user table
            nullable = false
    )
    private User seller;

    @ManyToOne //one buyer, many orders
    @JoinColumn(
            name = "buyer", //name in order table
            referencedColumnName = "user_id", //name in user table
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

    public Order() {

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