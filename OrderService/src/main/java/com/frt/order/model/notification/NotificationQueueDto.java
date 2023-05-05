package com.frt.order.model.notification;

import com.frt.order.model.user.FrtUserDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NotificationQueueDto {

    private Long orderId;
    private BigDecimal totalPrice;
    private List<NotificationSellerDto> notificationSellerDto;
    private FrtUserDto clientUser;

}
