package com.frt.product.persistence.entity;

import com.frt.product.model.product.PostProductRequest;
import com.frt.product.persistence.util.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "product_name")
    private String productName;

    private Integer quantity;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.status = Status.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public static Product transformRequestToEntity(PostProductRequest postProductRequest, Category category) {
        return Product.builder()
                .category(category)
                .sellerId(postProductRequest.getSellerId())
                .productName(postProductRequest.getProductName())
                .quantity(postProductRequest.getQuantity())
                .price(postProductRequest.getPrice())
                .build();
    }

}
