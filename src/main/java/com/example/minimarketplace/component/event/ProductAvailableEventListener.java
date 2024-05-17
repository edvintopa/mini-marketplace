package com.example.minimarketplace.component.event;

import com.example.minimarketplace.model.notification.Notification;
import com.example.minimarketplace.model.user.UserInterest;
import com.example.minimarketplace.repository.user.NotificationRepository;
import com.example.minimarketplace.repository.user.UserInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
@Component
public class ProductAvailableEventListener {

    @Autowired
    UserInterestRepository userInterestRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @EventListener
    public void handleProductAvailableEvent(ProductAvailableEvent event) {

        List<UserInterest> interests = userInterestRepository.findByInterest(event.getProductType());

        for (UserInterest user : interests) {
            notificationRepository.save(new Notification(
                    user.getUserId(),
                    event.getProductType()
            ));
        }
    }
}
