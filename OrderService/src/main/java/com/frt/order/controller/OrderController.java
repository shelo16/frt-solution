package com.frt.order.controller;

import com.frt.order.model.order.PostOrderRequest;
import com.frt.order.model.order.PostOrderResponse;
import com.frt.order.service.OrderFacade;
import com.frt.order.service.util.UriLocationBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping
    public ResponseEntity<PostOrderResponse> createOrder(@Valid @RequestBody(required = false) PostOrderRequest postOrderRequest) {
        PostOrderResponse postOrderResponse = orderFacade.createOrder(postOrderRequest);
        URI location = UriLocationBuilder.buildUri("/order/user/{clientId}", postOrderResponse.getClientId());

        return ResponseEntity
                .created(location)
                .body(postOrderResponse);
    }

}
