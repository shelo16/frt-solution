package com.frt.pad.model.orderdelivery;

import com.frt.pad.model.order.GetOrderResponse;
import com.frt.pad.model.util.Status;
import com.frt.pad.persistence.entity.OrderDelivery;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GetOrderDeliveryResponse {

    private GetOrderResponse getOrderResponse;
    private Status status;


    public static GetOrderDeliveryResponse transformEntityToResponse(OrderDelivery orderDelivery) {
        return GetOrderDeliveryResponse.builder()
                .getOrderResponse(GetOrderResponse.transformEntityToResponse(orderDelivery.getOrder()))
                .status(orderDelivery.getStatus())
                .build();
    }
}
