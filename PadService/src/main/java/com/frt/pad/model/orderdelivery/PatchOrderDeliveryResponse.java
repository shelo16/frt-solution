package com.frt.pad.model.orderdelivery;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PatchOrderDeliveryResponse {

    private String message;
    private Long orderDeliveryId;

}
