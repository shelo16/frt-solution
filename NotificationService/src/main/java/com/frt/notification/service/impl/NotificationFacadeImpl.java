package com.frt.notification.service.impl;

import com.frt.notification.model.NotificationQueueDto;
import com.frt.notification.model.PostNotificationRequest;
import com.frt.notification.model.util.NotifType;
import com.frt.notification.service.NotificationFacade;
import com.frt.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationFacadeImpl implements NotificationFacade {

    private DummyMailService dummyMailService;

    private DummySmsSender dummySmsSender;

    private NotificationService notificationService;

    @Override
    public void processQueueMessage(NotificationQueueDto notificationQueueDto) {

        log.info("Sending Email");
        String emailMessage = dummyMailService.sendEmail(notificationQueueDto);

        log.info("Sending sms");
        String smsMessage = dummySmsSender.sendSms(notificationQueueDto);

        log.info("Saving notifications batch");
        List<PostNotificationRequest> postNotificationRequests =
                buildNotificationRequests(notificationQueueDto, emailMessage, smsMessage);
        notificationService.saveNotificationBatch(postNotificationRequests);

    }

    private List<PostNotificationRequest> buildNotificationRequests(NotificationQueueDto notificationQueueDto,
                                                                    String emailMessage,
                                                                    String smsMessage) {
        // Build request for email notification
        PostNotificationRequest emailRequest = PostNotificationRequest.builder()
                .notifType(NotifType.EMAIL)
                .orderId(notificationQueueDto.getOrderId())
                .frtUserId(notificationQueueDto.getSellerUser().getUserId())
                .message(emailMessage)
                .build();

        // Build request for SMS notification
        PostNotificationRequest sms = PostNotificationRequest.builder()
                .notifType(NotifType.SMS)
                .orderId(notificationQueueDto.getOrderId())
                .frtUserId(notificationQueueDto.getClientUser().getUserId())
                .message(smsMessage)
                .build();

        return List.of(emailRequest, sms);
    }
}
