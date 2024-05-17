package com.example.minimarketplace.model.product.response;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ClothingCreateResponse {
    private UUID product_id;
    private HttpStatus httpStatus;

    public ClothingCreateResponse(UUID product_id, HttpStatus httpStatus) {
        this.product_id = product_id;
        this.httpStatus = httpStatus;
    }
    public UUID getProduct_id() {
        return product_id;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
