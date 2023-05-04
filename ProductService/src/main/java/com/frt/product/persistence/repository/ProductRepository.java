package com.frt.product.persistence.repository;

import com.frt.product.persistence.entity.Product;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @NonNull
    @Query("SELECT p FROM Product p WHERE (:productName IS NULL OR p.productName LIKE CONCAT('%', :productName, '%')) " +
            "AND (:priceFrom IS NULL OR p.price >= :priceFrom) " +
            "AND (:priceTo IS NULL OR p.price <= :priceTo) " +
            "AND (:categoryId IS NULL OR p.category.id = :categoryId) " +
            "ORDER BY p.productName ASC")
    Page<Product> findAll(@NonNull Pageable pageable,
                          @Param("productName") String productName,
                          @Param("priceFrom") BigDecimal priceFrom,
                          @Param("priceTo") BigDecimal priceTo,
                          @Param("categoryId") Long categoryId);

}
