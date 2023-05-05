package com.frt.notification.service.impl;

import com.frt.notification.model.NotificationQueueDto;
import com.frt.notification.model.NotificationResponse;
import com.frt.notification.model.util.NotifType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DummySmsSender {

    public NotificationResponse sendSms(NotificationQueueDto notificationQueueDto) {


        return buildAndSendSms(notificationQueueDto);
    }

    NotificationResponse buildAndSendSms(NotificationQueueDto notificationQueueDto) {

        //Send SMS to the client with information for the order – order ID and total price.
        String message = "Hey " +
                notificationQueueDto.getClientUser().getFirstName() + " " + notificationQueueDto.getClientUser().getLastName() + ", \n" +
                "Here's your order details : " +
                "Total Price : " + notificationQueueDto.getTotalPrice() + "\n" +
                "OrderId : " + notificationQueueDto.getOrderId() + "\n" +
                "Delivery Location : " +
                notificationQueueDto.getClientUser().getAddress();

        log.info("Sent SMS to Client : " + notificationQueueDto.getClientUser().getPhoneNumber());
        log.info("SMS Message : " + message);
        return NotificationResponse.builder()
                .userId(notificationQueueDto.getClientUser().getUserId())
                .message(message)
                .notifType(NotifType.SMS)
                .build();

    }

}
