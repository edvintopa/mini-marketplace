package com.example.minimarketplace.model.communication.request.cart;

import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
public class CartRequest {
    UUID productId;

    public CartRequest() {}    //workaround

    public CartRequest(String productId) {
        this.productId = UUID.fromString(productId);
    }

    public UUID getProductId() {
        return productId;
    }
}
