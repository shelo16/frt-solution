package com.frt.notification.service.impl;

import com.frt.notification.model.NotificationQueueDto;
import com.frt.notification.model.NotificationResponse;
import com.frt.notification.model.PostNotificationRequest;
import com.frt.notification.service.NotificationFacade;
import com.frt.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<NotificationResponse> emailNotificationResponseList = dummyMailService.sendEmail(notificationQueueDto);

        log.info("Sending sms");
        NotificationResponse smsNotificationResponse = dummySmsSender.sendSms(notificationQueueDto);

        emailNotificationResponseList.add(smsNotificationResponse);

        log.info("Saving notifications batch");
        List<PostNotificationRequest> postNotificationRequests =
                buildNotificationRequests(emailNotificationResponseList, notificationQueueDto);
        notificationService.saveNotificationBatch(postNotificationRequests);

    }

    private List<PostNotificationRequest> buildNotificationRequests(List<NotificationResponse> notificationResponseList,
                                                                    NotificationQueueDto notificationQueueDto) {
        return notificationResponseList.stream()
                .map(notificationResponse -> PostNotificationRequest.transformToNotificationRequest(notificationResponse, notificationQueueDto))
                .collect(Collectors.toList());
    }

}
