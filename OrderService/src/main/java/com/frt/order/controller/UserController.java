package com.frt.order.controller;

import com.frt.order.model.GetOrderResponse;
import com.frt.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order/user")
public class UserController {

    private final OrderService orderService;

    @GetMapping("/{clientId}")
    public ResponseEntity<List<GetOrderResponse>> getUserOrders(@Valid @PathVariable @Min(1) Long clientId) {

        return ResponseEntity.ok(orderService.getAllUserOrders(clientId));
    }

}
