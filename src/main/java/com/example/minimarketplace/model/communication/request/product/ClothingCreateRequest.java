package com.example.minimarketplace.model.communication.request.product;

public class ClothingCreateRequest {
    private String title;
    private String description;
    private String manufacturer;
    private double price;
    private String productcondition;


    private String productcolor;
    private String productImage;
    private String season;
    private String sex;
    private String size;
    private String type;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
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

    public String getproductImage() {
        return productImage;
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
