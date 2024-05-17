package com.example.minimarketplace.model.product;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductType {
    CLOTHING;

    @JsonValue
    public String toValue(){
        return this.name();
    }

}
