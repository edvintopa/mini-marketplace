package com.example.minimarketplace.model.product;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductStatus {
    AVAILABLE,
    NOT_AVAILABLE,
    ON_HOLD;

    @JsonValue
    public String toValue(){
        return this.name();
    }

}
