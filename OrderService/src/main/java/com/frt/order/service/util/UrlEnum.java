package com.frt.order.service.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UrlEnum {

    SCHEME("http://"),
    PRODUCT_STOCK_ENDPOINT("/order"),
    PRODUCT_BASE_URL("localhost:8082");

    private final String description;
}
