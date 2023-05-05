package com.frt.order.exception.model.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductException extends RuntimeException {

    private List<String> productNames;
    private String errorMessage;

}
