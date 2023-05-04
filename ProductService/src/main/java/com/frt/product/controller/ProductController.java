package com.frt.product.controller;

import com.frt.product.model.product.ProductFilterResponse;
import com.frt.product.model.product.ProductResponse;
import com.frt.product.service.ProductService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<ProductFilterResponse> filterProducts(@RequestParam(required = false) String productName,
                                                                @RequestParam(required = false) BigDecimal priceFrom,
                                                                @RequestParam(required = false) BigDecimal priceTo,
                                                                @RequestParam(required = false) Long categoryId,
                                                                @RequestParam int page,
                                                                @RequestParam int size) {
        return ResponseEntity.ok(productService.filter(productName, priceFrom, priceTo, categoryId, page, size));
    }


}
