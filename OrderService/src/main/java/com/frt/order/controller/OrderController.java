package com.frt.order.controller;

import com.frt.order.model.GetOrderResponse;
import com.frt.order.model.PostOrderRequest;
import com.frt.order.model.PostOrderResponse;
import com.frt.order.service.OrderService;
import com.frt.order.service.util.UriLocationBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderResponse> getOrder(@Valid @PathVariable @Min(1) Long orderId) {

        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<PostOrderResponse> createOrder(@Valid @RequestBody PostOrderRequest postOrderRequest) {
        PostOrderResponse postOrderResponse = orderService.createOrder(postOrderRequest);
        URI location = UriLocationBuilder.buildUri("/order/{orderId}", postOrderResponse.getOrderId());

        return ResponseEntity
                .created(location)
                .body(postOrderResponse);
    }

}
