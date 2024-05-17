package com.example.minimarketplace.component.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-16
 */
public class ProductAvailableEvent extends ApplicationEvent {

    private String type;

    public ProductAvailableEvent(Object source, String type) {
        super(source);
        this.type = type;
    }

    public String getProductType() {
        return type;
    }
}
