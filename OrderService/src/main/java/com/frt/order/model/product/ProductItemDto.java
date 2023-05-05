package com.frt.order.model.product;

import com.frt.order.persistence.entity.ProductItem;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItemDto {

    @Min(1)
    private Long sellerId;

    @NotEmpty(message = "Product Name must not be empty")
    private String productName;

    @Min(1)
    private Long productId;

    @Min(1)
    private int quantity;

    @Min(1)
    private BigDecimal price;

    public static ProductItemDto transformEntityToDto(ProductItem productItem) {
        return ProductItemDto.builder()
                .sellerId(productItem.getSellerId())
                .productName(productItem.getProductName())
                .productId(productItem.getProductId())
                .quantity(productItem.getQuantity())
                .price(productItem.getPrice())
                .build();
    }

}
