package com.frt.product.model.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductError {

    private String message;
    private List<String> productNameList;

}
