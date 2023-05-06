package com.frt.product.service.impl;

import com.frt.product.exception.model.customexception.GeneralException;
import com.frt.product.exception.util.FrtError;
import com.frt.product.model.product.*;
import com.frt.product.persistence.entity.Category;
import com.frt.product.persistence.entity.Product;
import com.frt.product.persistence.repository.CategoryRepository;
import com.frt.product.persistence.repository.ProductRepository;
import com.frt.product.service.ProductService;
import com.frt.product.service.util.PaginationUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final HttpServletResponse response;
    private final HttpServletRequest request;
    private final PaginationUtil paginationUtil;

    private final CategoryRepository categoryRepository;

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
                                        Long categoryId,
                                        int page,
                                        int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productList = productRepository.findAll(pageable, productName, priceFrom, priceTo, categoryId);
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

    @Override
    public void decrementStock(List<ProductItemDto> productItemDtoList) {
        log.info("Decrementing Stock for products : " + productItemDtoList.toString());
        List<Long> productIds = productItemDtoList.stream()
                .map(ProductItemDto::getProductId)
                .toList();

        List<Product> productEntityList = productRepository.findAllByProductIdIn(productIds);
        Map<Long, Product> productEntityMap = buildProductEntityMap(productEntityList);

        for (ProductItemDto dto : productItemDtoList) {
            Product product = productEntityMap.get(dto.getProductId());

            int productQuantity = product.getQuantity();
            int toDecreaseQuantity = dto.getQuantity();
            int decreasedQuantity = productQuantity - toDecreaseQuantity;

            if (decreasedQuantity < 0) {
                decreasedQuantity = 0;
            }
            product.setQuantity(decreasedQuantity);
        }

        log.info("Saving decreased stocks for products");
        productRepository.saveAll(productEntityList);
    }

    @Override
    public ProductError validateStock(List<ProductItemDto> productItemDtoList) {

        log.info("Validating Stock for items : " + productItemDtoList.toString());
        List<Long> productIds = productItemDtoList.stream()
                .map(ProductItemDto::getProductId)
                .toList();

        List<Product> productEntityList = productRepository.findAllByProductIdIn(productIds);
        Map<Long, Integer> productItemDtoMap = buildProductItemDtoMap(productItemDtoList);

        List<String> productNameList = new ArrayList<>();
        String message = FrtError.PRODUCT_QUANTITY_INVALID.getDescription();
        for (Product product : productEntityList) {
            int toDecreaseQuantity = productItemDtoMap.get(product.getProductId());
            if (product.getQuantity() < toDecreaseQuantity) {
                productNameList.add(product.getProductName());
            }
        }

        if (productNameList.isEmpty()) {
            message = "ok";
        }
        return ProductError.builder()
                .message(message)
                .productNameList(productNameList)
                .build();
    }

    @Override
    public PostProductResponse saveProduct(PostProductRequest postProductRequest) {
        Category category = categoryRepository
                .findById(postProductRequest.getCategoryId())
                .orElseThrow(() -> new GeneralException(FrtError.NO_CATEGORY_FOUND));
        Product product = Product.transformRequestToEntity(postProductRequest, category);
        Product savedProduct = productRepository.save(product);
        return PostProductResponse.builder()
                .productId(savedProduct.getProductId())
                .message("Created")
                .build();
    }

    private Map<Long, Product> buildProductEntityMap(List<Product> productEntityList) {
        return productEntityList.stream()
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));
    }

    private Map<Long, Integer> buildProductItemDtoMap(List<ProductItemDto> productItemDtoList) {
        return productItemDtoList.stream()
                .collect(Collectors.toMap(ProductItemDto::getProductId, ProductItemDto::getQuantity));
    }
}
