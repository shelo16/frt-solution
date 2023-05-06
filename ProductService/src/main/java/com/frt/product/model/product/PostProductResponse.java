package com.frt.product.model.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PostProductResponse {

    private Long productId;
    private String message;

}
