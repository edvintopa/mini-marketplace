package com.example.minimarketplace.model.communication.response.order;

import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
public class AddToCartResponse {
    private UUID productId;
    private UUID userId;

    public AddToCartResponse(UUID productId, UUID userId) {
        this.productId = productId;
        this.userId = userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getUserId() {
        return userId;
    }
}
