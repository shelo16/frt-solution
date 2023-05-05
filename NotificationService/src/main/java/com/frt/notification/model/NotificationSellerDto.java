package com.frt.notification.model;

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
