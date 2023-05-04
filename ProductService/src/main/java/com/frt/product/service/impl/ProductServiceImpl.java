package com.frt.product.service.impl;

import com.frt.product.exception.model.customexception.GeneralException;
import com.frt.product.exception.util.FrtError;
import com.frt.product.model.product.ProductResponse;
import com.frt.product.persistence.entity.Product;
import com.frt.product.persistence.repository.ProductRepository;
import com.frt.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse findById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new GeneralException(FrtError.NO_PRODUCT_FOUND));

        return ProductResponse
                .transformEntityToResponse(product);
    }

    @Override
    public List<ProductResponse> filter(String productName,
                                        BigDecimal priceFrom,
                                        BigDecimal priceTo) {

        Pageable pageable = PageRequest.of(0, 5);
        Page<Product> productList = productRepository.findAll(pageable, productName, priceFrom, priceTo);
        return productList.stream()
                .map(ProductResponse::transformEntityToResponse)
                .collect(Collectors.toList());

    }
}
