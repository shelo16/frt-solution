package com.frt.order.model.notification;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NotificationSellerDto {

    private Long sellerId;
    private String sellerEmail;
    private List<String> productName;

}
