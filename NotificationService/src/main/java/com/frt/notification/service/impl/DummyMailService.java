package com.frt.notification.service.impl;

import com.frt.notification.model.NotificationResponse;
import com.frt.notification.model.NotificationQueueDto;
import com.frt.notification.model.NotificationSellerDto;
import com.frt.notification.model.util.NotifType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DummyMailService {

    public List<NotificationResponse> sendEmail(NotificationQueueDto notificationQueueDto) {

        // Send Email to the Seller with information about the ordered products.
        List<NotificationSellerDto> notificationSellerDto =
                notificationQueueDto.getNotificationSellerDto();

        return buildAndSendMail(notificationSellerDto);
    }

    private List<NotificationResponse> buildAndSendMail(List<NotificationSellerDto> notificationSellerDto) {
        List<NotificationResponse> notificationResponseList = new ArrayList<>();
        for (NotificationSellerDto sellerDto : notificationSellerDto) {
            String message = "Hey " +
                    sellerDto.getSellerEmail() +
                    ", \n" +
                    "You Sold your Products : " +
                    sellerDto.getProductName().toString();

            log.info("Email Sent with Message : ");
            log.info(message);
            notificationResponseList.add(
                    NotificationResponse.builder()
                            .userId(sellerDto.getSellerId())
                            .message(message)
                            .notifType(NotifType.EMAIL)
                            .build());
        }
        return notificationResponseList;
    }

}
