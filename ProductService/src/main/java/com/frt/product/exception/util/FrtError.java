package com.frt.product.exception.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FrtError {

    INVALID_REQUEST("Invalid Request", HttpStatus.BAD_REQUEST),
    PRODUCT_QUANTITY_INVALID("Product quantity is less than requested amount", HttpStatus.BAD_REQUEST),
    NO_PRODUCT_FOUND("No product was found by given parameters", HttpStatus.BAD_REQUEST),
    NO_CATEGORY_FOUND("No Category was found with given parameters", HttpStatus.BAD_REQUEST);

    private final String description;
    private final HttpStatus status;
}
