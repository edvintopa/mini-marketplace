package com.example.minimarketplace.model.communication.request.order;

import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-18
 */
public class OrderRequest {
    UUID id;

    public OrderRequest() {}    //workaround

    public OrderRequest(String id) {
        this.id = UUID.fromString(id);
    }

    public UUID getId() {
        return id;
    }
}
