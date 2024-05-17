package com.example.minimarketplace.model.notification;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
@Entity
@Table(name ="notification")
public class Notification {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(
            name = "notification_id",
            updatable = false,
            nullable = false
    )
    private UUID notificationId;

    @Column(
            name = "user_id",
            updatable = false,
            nullable = false
    )
    private UUID userId;

    @Column(
            name = "category",
            nullable = false
    )
    private String type;

    @Column(
            name = "date_of_notification",
            nullable = false,
            updatable = false,
            columnDefinition = "DATE"
    )
    private Date dateOfNotification;

    @Column(
            name = "is_read",
            nullable = false
    )
    private boolean isRead;

    public Notification(UUID userId, String type) {
        this.userId = userId;
        this.type = type;
        dateOfNotification = new Date();
        isRead = false;
    }

    public Notification() {}    //default constructor workaround

    public UUID getNotificationId() {
        return notificationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public Date getDateOfNotification() {
        return dateOfNotification;
    }

    public boolean isRead() {
        return isRead;
    }

}
