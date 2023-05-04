package com.frt.product.persistence.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    ACTIVE("Active"),
    OUT_OF_STOCK("Out of stock"),
    DEACTIVATED("Deactivated");

    private final String description;

}
