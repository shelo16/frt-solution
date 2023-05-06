package com.frt.pad.controller.admin;

import com.frt.pad.model.orderdelivery.PatchOrderDeliveryResponse;
import com.frt.pad.service.OrderDeliveryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pad/seller")
public class AdminController {

    private final OrderDeliveryService orderDeliveryService;

    @PatchMapping("/{orderDeliveryId}")
    public ResponseEntity<PatchOrderDeliveryResponse> updateOrderStatus(@Valid @Min(1) Long orderDeliveryId,
                                                                        @RequestParam String status) {
        return ResponseEntity.ok(orderDeliveryService.updateStatus(orderDeliveryId, status));
    }
}
