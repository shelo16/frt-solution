package com.frt.product.model.product;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItemDto {

    @Min(1)
    private Long productId;
    private int quantity;
}
