package com.example.minimarketplace.model.product.products.clothing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author alexandrusom
 * @project mini-marketplace
 * @created 2024-05-17
 */
public enum ClothingSeason {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER;

    @JsonValue
    public String toValue() {
        return this.name();
    }

    @JsonCreator
    public static ClothingSeason forValue(String value) {
        return ClothingSeason.valueOf(value.toUpperCase());
    }
}
