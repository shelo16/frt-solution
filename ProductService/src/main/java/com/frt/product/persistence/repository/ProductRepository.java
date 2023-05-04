package com.frt.product.persistence.repository;

import com.frt.product.persistence.entity.Product;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @NonNull
    Page<Product> findAll(@NonNull Pageable pageable);

}
