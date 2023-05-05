package com.frt.order.service;

import com.frt.order.model.PostOrderRequest;
import com.frt.order.model.PostOrderResponse;

public interface OrderFacade {

    PostOrderResponse createOrder(PostOrderRequest postOrderRequest);

}
