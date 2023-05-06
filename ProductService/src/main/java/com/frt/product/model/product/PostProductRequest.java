package com.frt.product.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostProductRequest {

    @Min(1)
    private Long sellerId;
    @NotNull(message = "Please provide product name")
    private String productName;
    @Min(1)
    private Integer quantity;
    @Min(1)
    private BigDecimal price;
    @Min(1)
    private Long categoryId;

}
