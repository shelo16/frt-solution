package com.frt.pad.model.orderdelivery;

import com.frt.pad.persistence.entity.Order;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostOrderDeliveryRequest {

    @NotNull
    private Order order;

}
