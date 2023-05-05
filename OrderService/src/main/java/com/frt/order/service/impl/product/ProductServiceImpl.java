package com.frt.order.service.impl.product;

import com.frt.order.model.ProductError;
import com.frt.order.model.ProductItemDto;
import com.frt.order.service.ProductService;
import com.frt.order.service.util.UrlEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final RestTemplate restTemplate;

    @Override
    public ProductError checkStock(List<ProductItemDto> productItemDtoList) {
        ResponseEntity<ProductError> response = restTemplate.exchange(
                UrlEnum.SCHEME.getDescription() +
                        UrlEnum.PRODUCT_BASE_URL.getDescription() +
                        UrlEnum.PRODUCT_STOCK_ENDPOINT.getDescription(),
                HttpMethod.POST,
                new HttpEntity<>(productItemDtoList),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }
}
