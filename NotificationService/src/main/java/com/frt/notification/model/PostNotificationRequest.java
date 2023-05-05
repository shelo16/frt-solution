package com.frt.notification.model;

import com.frt.notification.model.util.NotifType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostNotificationRequest {


    private NotifType notifType;
    private Long orderId;
    private Long frtUserId;
    private String message;

    public static PostNotificationRequest transformToNotificationRequest(NotificationResponse notificationResponse,
                                                                         NotificationQueueDto notificationQueueDto) {
        return PostNotificationRequest.builder()
                .notifType(notificationResponse.getNotifType())
                .orderId(notificationQueueDto.getOrderId())
                .frtUserId(notificationQueueDto.getClientUser().getUserId())
                .message(notificationResponse.getMessage())
                .build();
    }
}
