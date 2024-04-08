package com.example.minimarketplace.model.dto.product;

import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-08
 */
public interface Product {
    public UUID getUUID();
    //public User getSeller();
    public String getTitle();
}
