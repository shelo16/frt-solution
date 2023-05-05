package com.frt.order.model.order;

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
}
