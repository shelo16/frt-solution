package com.frt.order.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long userId;
    private List<Long> productId;
    private BigDecimal totalPrice;
    //TODO Change to Model enum
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
