package com.example.minimarketplace.model.product.request;

import com.example.minimarketplace.model.product.ProductColor;
import com.example.minimarketplace.model.product.products.clothing.ClothingSeason;
import com.example.minimarketplace.model.product.products.clothing.ClothingSex;
import com.example.minimarketplace.model.product.products.clothing.ClothingSize;
import com.example.minimarketplace.model.product.products.clothing.ClothingType;

public class ClothingCreateRequest {
    private java.lang.String title;
    private java.lang.String description;
    private java.lang.String manufacturer;
    private double price;
    private String productcondition;
    private String productcolor;
    private String season;
    private String sex;
    private String size;
    private String type;

    public java.lang.String getTitle() {
        return title;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public java.lang.String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public String getProductcondition() {
        return productcondition;
    }

    public String getProductcolor() {
        return productcolor;
    }

    public String getSeason() {
        return season;
    }

    public String getSex() {
        return sex;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}
