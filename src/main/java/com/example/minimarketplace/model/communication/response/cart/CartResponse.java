package com.example.minimarketplace.model.communication.response.cart;

import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
public class CartResponse {
    private UUID productId;

    public CartResponse(UUID productId) {
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }

}
