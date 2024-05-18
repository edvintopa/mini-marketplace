package com.example.minimarketplace.model.communication.request.product;

import java.util.UUID;

public class ClothingGetProductRequest {
    private UUID productId;

    public ClothingGetProductRequest(String productId) {
        this.productId = UUID.fromString(productId);
    }
    public ClothingGetProductRequest(){

    }

    public UUID getProductId() {
        return productId;
    }
}
