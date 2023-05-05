package com.frt.order.model;

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
