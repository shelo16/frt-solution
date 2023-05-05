package com.frt.notification.model;

import com.frt.notification.model.util.NotifType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {

    private String message;
    private Long userId;
    private NotifType notifType;

}
