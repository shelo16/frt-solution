package com.frt.product.model.category;

import com.frt.product.persistence.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private String categoryName;

    public static CategoryResponse transformEntityToResponse(Category category) {
        return CategoryResponse.builder()
                .categoryName(category.getCategoryName())
                .build();
    }
}
