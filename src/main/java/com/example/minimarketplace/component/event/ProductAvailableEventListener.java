package com.example.minimarketplace.component.event;

import com.example.minimarketplace.model.product.ProductType;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
@Component
public class ProductAvailableEventListener {
    private ProductType interest = null;    //TODO: Mechanism to extract what the user is interested in

    @EventListener
    public void handleProductAvailableEvent(ProductAvailableEvent event) {
        if (event.getProductType() == interest) {
            //TODO: Mechanism to save data about notifications
        }

        //get userid and interests that match interest
    }
}
