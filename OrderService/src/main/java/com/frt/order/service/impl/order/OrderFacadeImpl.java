package com.frt.order.service.impl.order;

import com.frt.order.exception.model.customexception.ProductException;
import com.frt.order.model.PostOrderRequest;
import com.frt.order.model.PostOrderResponse;
import com.frt.order.model.ProductError;
import com.frt.order.service.OrderFacade;
import com.frt.order.service.OrderService;
import com.frt.order.service.ProductService;
import com.frt.order.service.impl.product.ProductMessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderFacadeImpl implements OrderFacade {

    private final ProductMessageSender productMessageSender;
    private final OrderService orderService;

    private final ProductService productService;

    @Override
    public PostOrderResponse createOrder(PostOrderRequest postOrderRequest) {

        ProductError productError =
                productService.checkStock(postOrderRequest.getProductItemDtoList());
        if (!"ok".equalsIgnoreCase(productError.getMessage())) {
            buildAndThrowError(productError);
        }

        // Save Order
        PostOrderResponse postOrderResponse = orderService.createOrder(postOrderRequest);

        // TODO: Send Message to RabbitMQ for Product/Notification/PAD services
        productMessageSender.send(postOrderRequest.getProductItemDtoList());


        return postOrderResponse;
    }

    private void buildAndThrowError(ProductError productError) {
        if (!productError.getProductNameList().isEmpty()) {
            log.error("Quantity is more than available stock");
            throw new ProductException(
                    productError.getProductNameList(),
                    productError.getMessage());
        }
    }
}
