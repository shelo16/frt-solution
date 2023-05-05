package com.frt.order.model.order;

import com.frt.order.model.product.ProductItemDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PostOrderRequest {

    @Min(1)
    private Long userId;

    @NotEmpty(message = "No products selected for order")
    private List<ProductItemDto> productItemDtoList;

}
