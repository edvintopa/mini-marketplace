package com.example.minimarketplace.model.communication.response.order;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
public class OrderResponse {

    private UUID orderId;
    private Date orderDate;
    private double total;
    private boolean isConfirmed;
    private UUID productId;

    public OrderResponse(UUID orderId,
                         Date orderDate,
                         double total,
                         boolean isConfirmed,
                         UUID productId) {

        this.orderId = orderId;
        this.orderDate = orderDate;
        this.total = total;
        this.isConfirmed = isConfirmed;
        this.productId = productId;
    }

    public UUID getOrderId() {
        return orderId;
    }
    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotal() {
        return total;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public UUID getProductId() {
        return productId;
    }
}
