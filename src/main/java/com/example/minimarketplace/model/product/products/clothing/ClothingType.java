package com.example.minimarketplace.model.product.products.clothing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-09
 */
public enum ClothingType {
    TSHIRT,
    SHIRT,
    HOODIE,
    TROUSERS,
    SHORTS,
    SKIRT,
    DRESS,
    FOOTWEAR,
    HEADWEAR,
    ACCESSORY,
    OTHER;

    @JsonValue
    public String toValue(){
        return this.name();
    }

    @JsonCreator
    public static ClothingSex forValue(String value){
        return ClothingSex.valueOf(value.toUpperCase());
    }
}
