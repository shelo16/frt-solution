package com.frt.product.model.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFilterResponse {

    private int totalPages;
    private long totalElements;
    private List<ProductResponse> productResponseList;

}
