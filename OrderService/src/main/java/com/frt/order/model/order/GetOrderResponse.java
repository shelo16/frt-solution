package com.frt.order.model.order;

import com.frt.order.model.product.ProductItemDto;
import com.frt.order.persistence.entity.Order;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderResponse {

    private Long userId;
    private BigDecimal totalPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<ProductItemDto> productItemDtoList;

    public static GetOrderResponse transformEntityToResponse(Order order) {
        return GetOrderResponse.builder()
                .userId(order.getClientId())
                .productItemDtoList(order.getProductItemList().stream().map(ProductItemDto::transformEntityToDto).collect(Collectors.toList()))
                .totalPrice(order.getTotalPrice())
                .createDate(order.getCreatedAt())
                .build();
    }
}
