package com.frt.notification.service;

import com.frt.notification.model.NotificationQueueDto;

public interface NotificationFacade {

    void processQueueMessage(NotificationQueueDto notificationQueueDto);

}
