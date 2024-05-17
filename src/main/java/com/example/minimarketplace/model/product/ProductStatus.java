package com.example.minimarketplace.model.product;

import com.example.minimarketplace.model.product.products.clothing.ClothingSex;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductStatus {
    AVAILABLE,
    NOT_AVAILABLE,
    ON_HOLD;

    @JsonValue
    public String toValue(){
        return this.name();
    }

    @JsonCreator
    public static ClothingSex forValue(String value){
        return ClothingSex.valueOf(value.toUpperCase());
    }


}
