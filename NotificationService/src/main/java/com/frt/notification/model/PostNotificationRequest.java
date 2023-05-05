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
}
