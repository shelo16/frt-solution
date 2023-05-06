package com.frt.product.service;

import com.frt.product.model.product.*;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductResponse findById(Long productId);

    ProductFilterResponse filter(String productName, BigDecimal priceFrom, BigDecimal priceTo, Long categoryId, int page, int size);

    void decrementStock(List<ProductItemDto> productItemDtoList);

    ProductError validateStock(List<ProductItemDto> productItemDtoList);

    PostProductResponse saveProduct(PostProductRequest postProductRequest);
}
