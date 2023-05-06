package com.frt.order.model.order;

import com.frt.order.persistence.entity.Order;
import com.frt.order.service.util.FrtSuccess;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostOrderResponse {

    private String message;
    private Long orderId;
    private BigDecimal totalPrice;
    private Long clientId;

    public static PostOrderResponse transformEntityToResponse(Order order) {
        return PostOrderResponse.builder()
                .message(FrtSuccess.CREATED.getDescription())
                .orderId(order.getOrderId())
                .totalPrice(order.getTotalPrice())
                .clientId(order.getClientId())
                .build();
    }
}
