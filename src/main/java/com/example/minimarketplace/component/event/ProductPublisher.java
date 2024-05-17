package com.example.minimarketplace.component.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */

/*CALL THIS VIA  productPublisher.notifyProductAvailability(savedProduct.getName(), savedProduct.getType());*/
@Component
public class ProductPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public ProductPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void notifyProductAvailability(String type) {
        ProductAvailableEvent event = new ProductAvailableEvent(this, type);
        eventPublisher.publishEvent(event);
    }
}
