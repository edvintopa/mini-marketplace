package com.example.minimarketplace.model.communication.response.order;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
public class GetMyOrdersResponse {

    private String sellerUsername;
    private Date orderDate;
    private double total;
    private boolean isConfirmed;
    private UUID productId;

    public GetMyOrdersResponse(String sellerUsername,
                               Date orderDate,
                               double total,
                               boolean isConfirmed,
                               UUID productId) {

        this.sellerUsername = sellerUsername;
        this.orderDate = orderDate;
        this.total = total;
        this.isConfirmed = isConfirmed;
        this.productId = productId;
    }

    public String getSellerUsername() {
        return sellerUsername;
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
