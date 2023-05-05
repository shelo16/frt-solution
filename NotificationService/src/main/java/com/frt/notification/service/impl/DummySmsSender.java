package com.frt.notification.service.impl;

import com.frt.notification.model.NotificationQueueDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DummySmsSender {

    public String sendSms(NotificationQueueDto notificationQueueDto) {
        String message = "Hey " +
                notificationQueueDto.getClientUser().getFirstName() +
                " \n" +
                notificationQueueDto.getClientUser().getLastName() +
                " \n" +
                "Bought Items: " +
                "Total Price : " +
                notificationQueueDto.getTotalPrice() +
                "\n" +
                "Product Names : " +
                notificationQueueDto.getProductNames() +
                "Will be delivered at : " +
                notificationQueueDto.getClientUser().getAddress();

        log.info("Sent SMS to Client : " + notificationQueueDto.getClientUser().getPhoneNumber());
        log.info("Email : ");
        log.info("Hey " + notificationQueueDto.getClientUser().getFirstName() + " " + notificationQueueDto.getClientUser().getLastName());
        log.info("Bought Items");
        log.info("Total Price : " + notificationQueueDto.getTotalPrice());
        log.info("Product Names : " + notificationQueueDto.getProductNames());
        log.info("Will be delivered at : " + notificationQueueDto.getClientUser().getAddress());

        return message;
    }

}
