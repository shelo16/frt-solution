package com.frt.product.controller;

import com.frt.product.model.product.ProductResponse;
import com.frt.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductResponse getProduct() {
        return productService.findById();
    }

    @GetMapping
    public List<ProductResponse> filterProducts() {
        return productService.filter();
    }


}
