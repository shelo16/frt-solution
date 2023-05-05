package com.frt.order.service;

import com.frt.order.model.order.PostOrderRequest;
import com.frt.order.model.order.PostOrderResponse;

public interface NotificationService {

    void sendNotificationMessage(PostOrderRequest postOrderRequest, PostOrderResponse postOrderResponse);

}
