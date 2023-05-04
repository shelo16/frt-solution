package com.frt.order.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostOrderResponse {

    private String message;
    private Long orderId;
}
