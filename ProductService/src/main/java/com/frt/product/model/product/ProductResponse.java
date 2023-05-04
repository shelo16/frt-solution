package com.frt.product.model.product;

import com.frt.product.persistence.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String productName;

    public static ProductResponse transformEntityToResponse(Product product) {
        return ProductResponse.builder()
                .productName(product.getProductName())
                .build();
    }

}
