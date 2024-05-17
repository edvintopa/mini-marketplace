package com.example.minimarketplace.model.product;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductColor {
    BLACK,
    WHITE,
    RED,
    BLUE,
    YELLOW,
    ORANGE,
    GREEN,
    PURPLE,
    GRAY,
    OTHER;

    @JsonValue
    public String toValue(){
        return this.name();
    }

}
