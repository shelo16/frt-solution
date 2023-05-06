package com.frt.product.controller.admin;

import com.frt.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products/admin")
public class AdminController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Long> saveCategory(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.saveCategory(name));
    }

}
