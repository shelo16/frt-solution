package com.frt.order.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.frt.order.model.product.ProductItemDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_item")
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "order_id")
    @JsonBackReference
    private Order order;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "product_name")
    private String productName;

    private Long productId;

    private int quantity;

    private BigDecimal price;

    public static ProductItem transformDtoToEntity(ProductItemDto productItemDto, Order order) {
        return ProductItem.builder()
                .productName(productItemDto.getProductName())
                .order(order)
                .quantity(productItemDto.getQuantity())
                .price(productItemDto.getPrice())
                .productId(productItemDto.getProductId())
                .sellerId(productItemDto.getSellerId())
                .build();
    }

}
