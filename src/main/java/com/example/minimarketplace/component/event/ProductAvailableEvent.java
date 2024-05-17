package com.example.minimarketplace.component.event;

import com.example.minimarketplace.model.product.ProductType;
import org.springframework.context.ApplicationEvent;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-16
 */
public class ProductAvailableEvent extends ApplicationEvent {

    private String productName;
    private ProductType type;

    public ProductAvailableEvent(Object source, String productName, ProductType type) {
        super(source);
        this.productName = productName;
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public ProductType getProductType() {
        return type;
    }
}
