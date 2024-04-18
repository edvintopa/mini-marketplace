package com.example.minimarketplace.model.dto.product.products.clothing;

import com.example.minimarketplace.model.dto.product.Product;
import com.example.minimarketplace.model.dto.product.ProductColor;
import com.example.minimarketplace.model.product.ProductCondition;
import com.example.minimarketplace.model.product.ProductStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa, Tiffany Dizdar
 * @project mini-marketplace
 * @created 2024-04-08
 */

@Entity
@Table(name ="garment")
public class Clothing extends Product {

    //Product attributes
    private UUID product_id;
    private UUID seller_id;
    private String title;
    private String description;
    private String manufacturer;
    private Date datePosted;
    private double price;
    private ProductCondition productCondition;
    private ProductColor productColor;
    private ProductStatus productStatus;

    //Clothing attributes
    @Enumerated(EnumType.STRING)
    @Column(
            name = "season",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private ClothingSeason season;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "sex",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private ClothingSex sex;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "size",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private ClothingSize size;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private ClothingType type;


    @Override
    public UUID getProduct_id() {
        return product_id;
    }

    @Override
    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
    }

    @Override
    public UUID getSeller_id() {
        return seller_id;
    }

    @Override
    public void setSeller_id(UUID seller_id) {
        this.seller_id = seller_id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Date getDatePosted() {
        return datePosted;
    }

    @Override
    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public ProductCondition getProductCondition() {
        return productCondition;
    }

    @Override
    public void setProductCondition(ProductCondition productCondition) {
        this.productCondition = productCondition;
    }

    @Override
    public ProductColor getProductColor() {
        return productColor;
    }

    @Override
    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }

    @Override
    public ProductStatus getProductStatus() {
        return productStatus;
    }

    @Override
    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public ClothingSeason getSeason() {
        return season;
    }

    public void setSeason(ClothingSeason season) {
        this.season = season;
    }

    public ClothingSex getSex() {
        return sex;
    }

    public void setSex(ClothingSex sex) {
        this.sex = sex;
    }

    public ClothingSize getSize() {
        return size;
    }

    public void setSize(ClothingSize size) {
        this.size = size;
    }

    public ClothingType getType() {
        return type;
    }

    public void setType(ClothingType type) {
        this.type = type;
    }


}
