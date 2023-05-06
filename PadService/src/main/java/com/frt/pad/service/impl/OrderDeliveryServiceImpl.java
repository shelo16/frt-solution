package com.frt.pad.service.impl;

import com.frt.pad.exception.model.customexception.GeneralException;
import com.frt.pad.exception.util.FrtError;
import com.frt.pad.model.orderdelivery.GetOrderDeliveryResponse;
import com.frt.pad.model.orderdelivery.PatchOrderDeliveryResponse;
import com.frt.pad.model.orderdelivery.PostOrderDeliveryResponse;
import com.frt.pad.model.util.Status;
import com.frt.pad.persistence.entity.Order;
import com.frt.pad.persistence.entity.OrderDelivery;
import com.frt.pad.persistence.repository.OrderDeliveryRepository;
import com.frt.pad.persistence.repository.OrderRepository;
import com.frt.pad.service.OrderDeliveryService;
import com.frt.pad.service.util.FrtSuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

    private final OrderDeliveryRepository orderDeliveryRepository;

    private final OrderRepository orderRepository;

    @Override
    public PostOrderDeliveryResponse createOrderDelivery(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GeneralException(FrtError.ORDER_NOT_FOUND));

        OrderDelivery orderDelivery = OrderDelivery.builder()
                .order(order)
                .build();

        OrderDelivery savedOrderDelivery =
                orderDeliveryRepository.save(orderDelivery);

        return PostOrderDeliveryResponse.builder()
                .orderDeliveryId(savedOrderDelivery.getOrderDeliveryId())
                .message(FrtSuccess.CREATED.getDescription())
                .build();
    }

    @Override
    public List<GetOrderDeliveryResponse> findByUserId(Long clientId) {
        return orderDeliveryRepository.findByClientId(clientId).stream()
                .map(GetOrderDeliveryResponse::transformEntityToResponse)
                .toList();
    }

    @Override
    public PatchOrderDeliveryResponse updateStatus(Long orderDeliveryId, String status) {
        OrderDelivery orderDelivery = orderDeliveryRepository
                .findById(orderDeliveryId)
                .orElseThrow(() -> new GeneralException(FrtError.ORDER_DELIVERY_NOT_FOUND));

        if (status.equalsIgnoreCase(orderDelivery.getStatus().name())) {
            throw new GeneralException(FrtError.ORDER_DELIVERY_STATUS_NOT_CHANGED);
        }

        try {
            orderDelivery.setStatus(Status.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            log.error("Invalid Status parameter");
        }

        OrderDelivery updatedOrderDeliver =
                orderDeliveryRepository.save(orderDelivery);

        return PatchOrderDeliveryResponse.builder()
                .orderDeliveryId(updatedOrderDeliver.getOrderDeliveryId())
                .message(FrtSuccess.UPDATED.getDescription())
                .build();

    }
}
