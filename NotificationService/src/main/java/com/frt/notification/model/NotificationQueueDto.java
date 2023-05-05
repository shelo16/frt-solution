package com.frt.notification.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationQueueDto {

    private Long orderId;
    private BigDecimal totalPrice;
    private List<String> productNames;
    private FrtUserDto sellerUser;
    private FrtUserDto clientUser;

}
