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
    String getTitle();
    String getDescription();
    Date getDatePosted();
    Date getDateOfProduction();
    double getPrice();
    ProductCondition getProductCondition();
    ProductColor getProductColor();
    ProductStatus getProductStatus();
    void setProduct_id(UUID product_id);
    void setTitle(String title);
    void setDescription(String description);
    void setDatePosted(Date datePosted);
    void setDateOfProduction(Date dateOfProduction);
    void setPrice(double price);
    void setProductCondition(ProductCondition productCondition);
    void setProductColor(ProductColor productColor);
    void setProductStatus(ProductStatus productStatus);
}
