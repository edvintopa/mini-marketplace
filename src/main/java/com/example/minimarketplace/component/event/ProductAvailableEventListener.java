package com.example.minimarketplace.component.event;

import com.example.minimarketplace.model.notification.Notification;
import com.example.minimarketplace.repository.NotificationRepository;
import com.example.minimarketplace.repository.UserInterestRepository;
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
        //get userid and interests that match interest
        List<UUID> users = userInterestRepository.findUsersInterestedIn(event.getProductType());

        //save to database notification
        for (UUID user : users) {
            notificationRepository.save(new Notification(
                    user,
                    event.getProductType()
            ));
        }
    }
}
