package com.frt.product.service;

import com.frt.product.model.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();

    Long saveCategory(String categoryName);
}
