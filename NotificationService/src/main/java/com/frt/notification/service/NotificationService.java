package com.frt.notification.service;

import com.frt.notification.model.PostNotificationRequest;

import java.util.List;

public interface NotificationService {

    void saveNotificationBatch(List<PostNotificationRequest> postNotificationRequest);

}
