package com.frt.order.service;

import com.frt.order.model.GetOrderResponse;
import com.frt.order.model.PostOrderRequest;
import com.frt.order.model.PostOrderResponse;

import java.util.List;

public interface OrderService {

    List<GetOrderResponse> getAllUserOrders(Long clientId);

    PostOrderResponse createOrder(PostOrderRequest postOrderRequest);

    GetOrderResponse getOrderById(Long orderId);

}
