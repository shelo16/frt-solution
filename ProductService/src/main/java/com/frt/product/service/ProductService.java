package com.frt.product.service;

import com.frt.product.model.product.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductResponse findById(Long productId);

    List<ProductResponse> filter(String productName, BigDecimal priceFrom, BigDecimal priceTo);

}
