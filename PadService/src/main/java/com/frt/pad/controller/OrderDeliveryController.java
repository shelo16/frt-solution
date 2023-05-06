package com.frt.pad.controller;

import com.frt.pad.model.orderdelivery.GetOrderDeliveryResponse;
import com.frt.pad.service.OrderDeliveryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/delivery")
public class OrderDeliveryController {

    private final OrderDeliveryService orderDeliveryService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<GetOrderDeliveryResponse>> getOrderDeliveryById(@Valid @PathVariable @Min(1) Long userId) {
        return ResponseEntity.ok(orderDeliveryService.findByUserId(userId));
    }
}
