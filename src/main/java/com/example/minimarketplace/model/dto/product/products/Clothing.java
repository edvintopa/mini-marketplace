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
public class Clothing implements Product {
    @Override
    public UUID getProduct_id() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Date getDatePosted() {
        return null;
    }

    @Override
    public Date getDateOfProduction() {
        return null;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public ProductCondition getProductCondition() {
        return null;
    }

    @Override
    public ProductColor getProductColor() {
        return null;
    }

    @Override
    public ProductStatus getProductStatus() {
        return null;
    }

    @Override
    public void setProduct_id(UUID product_id) {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public void setDatePosted(Date datePosted) {

    }

    @Override
    public void setDateOfProduction(Date dateOfProduction) {

    }

    @Override
    public void setPrice(double price) {

    }

    @Override
    public void setProductCondition(ProductCondition productCondition) {

    }

    @Override
    public void setProductColor(ProductColor productColor) {

    }

    @Override
    public void setProductStatus(ProductStatus productStatus) {

    }
}
