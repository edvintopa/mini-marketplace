package com.example.minimarketplace.model.communication.request.product;

public class ClothingFilterRequest {
    private String clothingType;
    private String productCondition;
    private double minPrice;
    private double maxPrice;


    public String getClothingType() {
        return clothingType;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }
}
