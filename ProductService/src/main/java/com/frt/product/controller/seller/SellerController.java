package com.frt.product.controller.seller;


import com.frt.product.model.product.PostProductRequest;
import com.frt.product.model.product.PostProductResponse;
import com.frt.product.service.ProductService;
import com.frt.product.service.util.UriLocationBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/products/seller")
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<PostProductResponse> saveProduct(@Valid @RequestBody PostProductRequest postProductRequest) {
        PostProductResponse postProductResponse = productService.saveProduct(postProductRequest);
        URI location = UriLocationBuilder.buildUri("/products/seller/{productId}", postProductResponse.getProductId());
        return ResponseEntity.created(location).body(postProductResponse);
    }

}
