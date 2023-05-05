package com.frt.order.service;

import com.frt.order.model.ProductError;
import com.frt.order.model.ProductItemDto;

import java.util.List;

public interface ProductService {

    ProductError checkStock(List<ProductItemDto> productItemDtoList);

}
