package com.example.minimarketplace.model.dto.product;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-08
 */
public abstract class ProductFactory {
    public abstract Product createProduct(String type, UUID product_id, String title, String description, Date datePosted,
                                          Date dateOfProduction, double price, ProductCondition productCondition, ProductColor productColor,
                                          ProductStatus productStatus);
}
