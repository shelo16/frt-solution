package com.frt.product.service;

import com.frt.product.model.product.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse findById();

    List<ProductResponse> filter();

}
