package com.frt.notification.persistence.entity;

import com.frt.notification.model.PostNotificationRequest;
import com.frt.notification.model.util.NotifType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(name = "notification_type")
    @Enumerated(EnumType.STRING)
    private NotifType notifType;

    private Long orderId;
    private Long frtUserId;
    private String message;

    public static Notification transformNotificationRequestToEntity(PostNotificationRequest notificationRequest) {
        return Notification.builder()
                .notifType(notificationRequest.getNotifType())
                .orderId(notificationRequest.getOrderId())
                .frtUserId(notificationRequest.getFrtUserId())
                .message(notificationRequest.getMessage())
                .build();
    }
}
