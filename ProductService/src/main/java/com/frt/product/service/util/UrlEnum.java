package com.frt.product.service.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UrlEnum {

    SCHEME("http://"),
    PRODUCT_STOCK_ENDPOINT("/products"),
    PRODUCT_BASE_URL("localhost:8081");

    private final String description;
}
