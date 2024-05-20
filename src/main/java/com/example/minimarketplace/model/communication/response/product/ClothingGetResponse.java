package com.example.minimarketplace.model.communication.response.product;

import java.util.UUID;

public class ClothingGetResponse {
    private UUID productId;
    private String title;
    private Enum ProductType;
    private double price;


    public ClothingGetResponse(UUID productId, Enum ProductType, String title, double price) {
        this.productId = productId;
        this.ProductType = ProductType;
        this.title = title;
        this.price = price;
    }

    public ClothingGetResponse(UUID productId, String title, double price) {
        this.productId = productId;
        this.ProductType = ProductType;
        this.title = title;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public Enum getProductType() {
        return ProductType;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }


}
