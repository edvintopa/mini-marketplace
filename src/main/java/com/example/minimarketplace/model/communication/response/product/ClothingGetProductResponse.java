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
    private Date date_posted;
    private String imagePath;
    private String type;
    private String manufacturer;
    private String sex;
    private String season;
    private String productSize;
    private String productCondition;
    public ClothingGetProductResponse(UUID productId,
                                      String title,
                                      double price,
                                      String status,
                                      String sellerName,
                                      String description,
                                      Date date_posted,
                                      String imagePath,
                                      String type,
                                      String manufacturer,
                                      String sex,
                                      String season,
                                      String productSize,
                                      String productCondition) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.status = status;
        this.sellerName = sellerName;
        this.description = description;
        this.date_posted = date_posted;
        this.imagePath = imagePath;
        this.type = type;
        this.manufacturer = manufacturer;
        this.sex = sex;
        this.season = season;
        this.productSize = productSize;
        this.productCondition = productCondition;
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

    public String getType() {
        return type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSex() {
        return sex;
    }

    public String getSeason() {
        return season;
    }

	public String getImagePath() {
		return imagePath;
	}

	public String getProductSize() {
		return productSize;
	}

	public String getProductCondition() {
		return productCondition;
	}
}


