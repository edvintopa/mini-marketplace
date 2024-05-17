package com.example.minimarketplace.component.event;

import com.example.minimarketplace.model.product.ProductType;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
@Component
public class ProductPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public ProductPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void notifyProductAvailability(String productName, ProductType type) {
        ProductAvailableEvent event = new ProductAvailableEvent(this, productName, type);
        eventPublisher.publishEvent(event);
    }
}
