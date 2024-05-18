package com.example.minimarketplace.model.communication.response.product;

import java.util.UUID;

public class ClothingGetResponse {
    private UUID productId;
    private String title;
    private double price;


    public ClothingGetResponse(UUID productId, String title, double price) {
        this.productId = productId;
        this.title = title;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}
