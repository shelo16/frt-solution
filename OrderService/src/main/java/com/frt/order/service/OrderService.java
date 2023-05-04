package com.frt.order.service;

import com.frt.order.model.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllUserOrders(Long userId);

}
