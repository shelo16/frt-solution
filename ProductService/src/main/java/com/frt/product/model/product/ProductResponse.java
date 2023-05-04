package com.frt.product.model.product;

import com.frt.product.model.category.CategoryResponse;
import com.frt.product.persistence.entity.Product;
import com.frt.product.persistence.util.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private Status status;
    private CategoryResponse categoryResponse;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductResponse transformEntityToResponse(Product product) {
        return ProductResponse.builder()
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .status(product.getStatus())
                .categoryResponse(CategoryResponse.transformEntityToResponse(product.getCategory()))
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

}
