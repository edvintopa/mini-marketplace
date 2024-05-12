package com.example.minimarketplace.model.user.response;

import org.springframework.http.HttpStatus;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-12
 */

public class ErrorResponse {
    HttpStatus httpStatus;
    String message;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}