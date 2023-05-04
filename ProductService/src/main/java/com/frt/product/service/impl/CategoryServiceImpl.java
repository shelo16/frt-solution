package com.frt.product.service.impl;

import com.frt.product.model.category.CategoryResponse;
import com.frt.product.persistence.repository.CategoryRepository;
import com.frt.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<CategoryResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(CategoryResponse::transformEntityToResponse)
                .collect(Collectors.toList());
    }
}
