package com.frt.order.service;

import com.frt.order.model.order.PostOrderRequest;
import com.frt.order.model.order.PostOrderResponse;

public interface OrderFacade {

    PostOrderResponse createOrder(PostOrderRequest postOrderRequest);

}
