package com.example.minimarketplace.model.product.products.vehicle;

import com.example.minimarketplace.model.dto.product.Product;
import com.example.minimarketplace.model.dto.product.ProductColor;
import com.example.minimarketplace.model.product.ProductCondition;
import com.example.minimarketplace.model.product.ProductStatus;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-09
 */
public class Vehicle implements Product {
    //Product attributes
    private UUID product_id;
    private UUID seller_id;
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

    //Vehicle attributes
    private VehicleType type;
    private VehicleDrivetrain drivetrain;
    private VehicleFuel fuel;
    private VehicleTransmission transmission;
    private double milage;
    private int numberOfOwners;

    //getters
    @Override
    public UUID getProductID() {
        return product_id;
    }

    @Override
    public UUID getSellerID() {
        return seller_id;
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

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleDrivetrain getDrivetrain() {
        return drivetrain;
    }

    public VehicleFuel getFuel() {
        return fuel;
    }

    public VehicleTransmission getTransmission() {
        return transmission;
    }

    public double getMilage() {
        return milage;
    }

    public int getNumberOfOwners() {
        return numberOfOwners;
    }

    //setters
    @Override
    public void setProductID(UUID productID) {
        this.product_id = productID;
    }

    @Override
    public void setSellerID(UUID sellerID) {
        this.seller_id = sellerID;
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

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setDrivetrain(VehicleDrivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    public void setFuel(VehicleFuel fuel) {
        this.fuel = fuel;
    }

    public void setTransmission(VehicleTransmission transmission) {
        this.transmission = transmission;
    }

    public void setMilage(double milage) {
        this.milage = milage;
    }

    public void setNumberOfOwners(int numberOfOwners) {
        this.numberOfOwners = numberOfOwners;
    }
}
