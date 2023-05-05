package com.frt.notification.service.impl;

import com.frt.notification.model.NotificationQueueDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DummyMailService {

    public String sendEmail(NotificationQueueDto notificationQueueDto) {
        String message = "Hey " +
                notificationQueueDto.getSellerUser().getFirstName() +
                " \n" +
                notificationQueueDto.getSellerUser().getLastName() +
                " \n" +
                "Sold Items to : " +
                notificationQueueDto.getClientUser().getEmail() +
                "\n" +
                "Total Price : " +
                notificationQueueDto.getTotalPrice() +
                "\n" +
                "Product Names : " +
                notificationQueueDto.getProductNames();

        log.info("Sent Email to Seller : " + notificationQueueDto.getSellerUser().getEmail());
        log.info("Email : ");
        log.info("Hey " + notificationQueueDto.getSellerUser().getFirstName() + " " + notificationQueueDto.getSellerUser().getLastName());
        log.info("Sold Items to : " + notificationQueueDto.getClientUser().getEmail());
        log.info("Total Price : " + notificationQueueDto.getTotalPrice());
        log.info("Product Names : " + notificationQueueDto.getProductNames());
        return message;
    }

}
