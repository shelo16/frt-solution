package com.frt.product.service;

import com.frt.product.model.product.ProductFilterResponse;
import com.frt.product.model.product.ProductResponse;

import java.math.BigDecimal;

public interface ProductService {

    ProductResponse findById(Long productId);

    ProductFilterResponse filter(String productName, BigDecimal priceFrom, BigDecimal priceTo, Long categoryId, int page, int size);

}
