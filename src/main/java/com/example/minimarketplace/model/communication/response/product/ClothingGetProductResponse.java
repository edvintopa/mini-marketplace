package com.example.minimarketplace.model.communication.response.product;

import java.util.Date;
import java.util.UUID;

public class ClothingGetProductResponse {
    private UUID productId;
    private String title;
    private double price;
    private String status;
    private String sellerName;
    private String description;
    private String productSize;
    private String productCondition;
    private Date date_posted;

    public ClothingGetProductResponse(UUID productId,
                                      String title,
                                      double price,
                                      String status,
                                      String sellerName,
                                      String description,
                                      String productSize,
                                      String productCondition,
                                      Date date_posted) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.status = status;
        this.sellerName = sellerName;
        this.description = description;
        this.productSize = productSize;
        this.productCondition =productCondition;
        this.date_posted = date_posted;
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

    public String getStatus() {
        return status;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_posted() {
        return date_posted;
    }

    public String getProductSize() {
        return productSize;
    }

    public String getProductCondition() {
        return productCondition;
    }
}
