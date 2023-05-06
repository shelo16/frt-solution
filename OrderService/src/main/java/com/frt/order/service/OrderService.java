package com.frt.order.service;

import com.frt.order.model.order.GetOrderResponse;
import com.frt.order.model.order.PostOrderRequest;
import com.frt.order.model.order.PostOrderResponse;

import java.util.List;

public interface OrderService {

    List<GetOrderResponse> getAllUserOrders(Long clientId);

    PostOrderResponse saveOrder(PostOrderRequest postOrderRequest);

}
