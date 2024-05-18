package com.example.minimarketplace.model.product.products.electronics;

import com.example.minimarketplace.model.product.*;
import com.example.minimarketplace.model.user.User;

import java.util.Date;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-08
 */
public class Electronics extends Product {

    //Electronics attributes
    private ElectronicsType type;

    public Electronics(User seller_id, String title, String description, String manufacturer, Date datePosted, double price, ProductCondition productCondition, ProductColor productColor, ProductStatus productStatus, String productImage) {
        super(seller_id, title, description, manufacturer, datePosted, price, productCondition, productColor, productStatus, productImage);
    }
}
