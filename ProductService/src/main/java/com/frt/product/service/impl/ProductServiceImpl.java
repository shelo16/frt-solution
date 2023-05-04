package com.frt.product.service.impl;

import com.frt.product.exception.model.customexception.GeneralException;
import com.frt.product.exception.util.FrtError;
import com.frt.product.model.product.ProductFilterResponse;
import com.frt.product.model.product.ProductResponse;
import com.frt.product.persistence.entity.Product;
import com.frt.product.persistence.repository.ProductRepository;
import com.frt.product.service.ProductService;
import com.frt.product.service.util.PaginationUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final HttpServletResponse response;
    private final HttpServletRequest request;
    private final PaginationUtil paginationUtil;

    @Override
    public ProductResponse findById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new GeneralException(FrtError.NO_PRODUCT_FOUND));

        return ProductResponse
                .transformEntityToResponse(product);
    }

    @Override
    public ProductFilterResponse filter(String productName,
                                        BigDecimal priceFrom,
                                        BigDecimal priceTo,
                                        int page,
                                        int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productList = productRepository.findAll(pageable, productName, priceFrom, priceTo);
        List<ProductResponse> productResponseList = productList.stream()
                .map(ProductResponse::transformEntityToResponse).toList();

        int totalPages = productList.getTotalPages();
        long totalElements = productList.getTotalElements();

        paginationUtil.addPaginationLinksHeader(page,
                productList.getTotalPages(),
                size,
                request,
                response);

        return ProductFilterResponse.builder()
                .productResponseList(productResponseList)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }
}
