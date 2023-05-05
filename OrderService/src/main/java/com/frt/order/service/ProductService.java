package com.frt.order.service;

import com.frt.order.model.product.ProductError;
import com.frt.order.model.product.ProductItemDto;

import java.util.List;

public interface ProductService {

    ProductError checkStock(List<ProductItemDto> productItemDtoList);

}
