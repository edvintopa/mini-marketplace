package com.example.minimarketplace.model.communication.response;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-19
 */

import java.util.Date;
public class NotificationResponse {
    private String category;
    private Date dateOfNotification;

    public NotificationResponse(String category, Date dateOfNotification) {
        this.category = category;
        this.dateOfNotification = dateOfNotification;
    }

    public String getCategory() {
        return category;
    }

    public Date getDateOfNotification() {
        return dateOfNotification;
    }
}
