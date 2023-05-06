package com.frt.pad.service;

import com.frt.pad.model.orderdelivery.GetOrderDeliveryResponse;
import com.frt.pad.model.orderdelivery.PatchOrderDeliveryResponse;
import com.frt.pad.model.orderdelivery.PostOrderDeliveryResponse;

import java.util.List;

public interface OrderDeliveryService {

    PostOrderDeliveryResponse createOrderDelivery(Long orderId);

    List<GetOrderDeliveryResponse> findByUserId(Long userId);

    PatchOrderDeliveryResponse updateStatus(Long orderDeliveryId, String status);
}
