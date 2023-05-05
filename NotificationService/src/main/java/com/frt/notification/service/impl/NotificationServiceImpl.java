package com.frt.notification.service.impl;

import com.frt.notification.model.PostNotificationRequest;
import com.frt.notification.persistence.entity.Notification;
import com.frt.notification.persistence.repository.NotificationRepository;
import com.frt.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    @Override
    public void saveNotificationBatch(List<PostNotificationRequest> postNotificationRequestList) {
        List<Notification> notificationEntityList = buildNotification(postNotificationRequestList);
        repository.saveAll(notificationEntityList);
    }

    private List<Notification> buildNotification(List<PostNotificationRequest> postNotificationRequestList) {
        return postNotificationRequestList.stream()
                .map(Notification::transformNotificationRequestToEntity)
                .collect(Collectors.toList());
    }


}
