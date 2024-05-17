package com.example.minimarketplace.model.communication.request.cart;

import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
public class AddToCartRequest {
    UUID productId;

    public AddToCartRequest() {}    //workaround

    public AddToCartRequest(String productId) {
        this.productId = UUID.fromString(productId);
    }

    public UUID getProductId() {
        return productId;
    }
}
