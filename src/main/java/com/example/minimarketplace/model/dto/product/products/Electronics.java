package com.example.minimarketplace.model.dto.product.products;

import com.example.minimarketplace.model.dto.product.Product;
import com.example.minimarketplace.model.dto.product.ProductColor;
import com.example.minimarketplace.model.dto.product.ProductCondition;
import com.example.minimarketplace.model.dto.product.ProductStatus;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-08
 */
public class Electronics implements Product {
    private UUID product_id;
    private String title;
    private String description;
    private String manufacturer;
    private String model;
    private Date datePosted;
    private Date dateOfProduction;
    private double price;
    private ProductCondition productCondition;
    private ProductColor productColor;
    private ProductStatus productStatus;

    //getters
    @Override
    public UUID getProduct_id() {
        return product_id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public Date getDatePosted() {
        return datePosted;
    }

    @Override
    public Date getModelYear() {
        return dateOfProduction;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public ProductCondition getProductCondition() {
        return productCondition;
    }

    @Override
    public ProductColor getProductColor() {
        return productColor;
    }

    @Override
    public ProductStatus getProductStatus() {
        return productStatus;
    }

    //setters
    @Override
    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public void setModelYear(Date modelYear) {
        this.dateOfProduction = modelYear;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setProductCondition(ProductCondition productCondition) {
        this.productCondition = productCondition;
    }

    @Override
    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }

    @Override
    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
