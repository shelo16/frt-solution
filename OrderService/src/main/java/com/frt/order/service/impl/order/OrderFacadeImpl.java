package com.frt.order.service.impl.order;

import com.frt.order.exception.model.customexception.ProductException;
import com.frt.order.model.order.PostOrderRequest;
import com.frt.order.model.order.PostOrderResponse;
import com.frt.order.model.product.ProductError;
import com.frt.order.service.NotificationService;
import com.frt.order.service.OrderFacade;
import com.frt.order.service.OrderService;
import com.frt.order.service.ProductService;
import com.frt.order.service.impl.rabbitmq.PadMessageSender;
import com.frt.order.service.impl.rabbitmq.ProductMessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderFacadeImpl implements OrderFacade {

    private final ProductMessageSender productMessageSender;
    private final PadMessageSender padMessageSender;
    private final NotificationService notificationService;
    private final OrderService orderService;
    private final ProductService productService;

    @Override
    public PostOrderResponse createOrder(PostOrderRequest postOrderRequest) {

        ProductError productError =
                productService.checkStock(postOrderRequest.getProductItemDtoList());

        if (!"ok".equalsIgnoreCase(productError.getMessage())) {
            buildAndThrowException(productError);
        }

        // Save Order
        PostOrderResponse postOrderResponse = orderService.saveOrder(postOrderRequest);

        // Send message to ProductService
        log.info("Sending message to ProductService");
        productMessageSender.send(postOrderRequest.getProductItemDtoList());

        // Send message to NotificationService
        log.info("Sending message to NotificationService");
        notificationService.sendNotificationMessage(postOrderRequest, postOrderResponse);

        // Send message to PadService
        log.info("Sending message to PadService");
        padMessageSender.send(postOrderResponse.getOrderId());

        return postOrderResponse;
    }

    private void buildAndThrowException(ProductError productError) {
        if (!productError.getProductNameList().isEmpty()) {
            log.error("Quantity is more than available stock");
            throw new ProductException(
                    productError.getProductNameList(),
                    productError.getMessage());
        }
    }
}
