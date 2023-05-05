package com.frt.product.model.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItemDto {

    private Long productId;
    private int quantity;
}
