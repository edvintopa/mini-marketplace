package com.example.minimarketplace.model.dto.product;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-08
 */
public interface Product {
    UUID getProduct_id();
    UUID getSeller_id();
    String getTitle();
    String getDescription();
    String getManufacturer();
    String getModel();
    Date getDatePosted();
    Date getModelYear();
    double getPrice();
    ProductCondition getProductCondition();
    ProductColor getProductColor();
    ProductStatus getProductStatus();
    void setProduct_id(UUID product_id);
    void setSeller_id(UUID seller_id);
    void setTitle(String title);
    void setDescription(String description);
    void setManufacturer(String manufacturer);
    void setModel(String model);
    void setDatePosted(Date datePosted);
    void setModelYear(Date modelYear);
    void setPrice(double price);
    void setProductCondition(ProductCondition productCondition);
    void setProductColor(ProductColor productColor);
    void setProductStatus(ProductStatus productStatus);
}
