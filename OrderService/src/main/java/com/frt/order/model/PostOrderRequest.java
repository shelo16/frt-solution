package com.frt.order.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostOrderRequest {

    // TODO : validation
    private Long userId;

    @NotEmpty(message = "No products selected for order")
    private List<ProductItemDto> productItemDtoList;

}
