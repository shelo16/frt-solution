package com.frt.pad.model.order;

import com.frt.pad.persistence.entity.ProductItem;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItemDto {

    private Long sellerId;
    private String productName;
    private Long productId;
    private int quantity;
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
