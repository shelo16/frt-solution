package com.frt.product.service.impl;

import com.frt.product.model.product.ProductItemDto;
import com.frt.product.service.ProductService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQListener {

    private final ProductService productService;

    @RabbitListener(queues = "${rabbitmq.product.queue.name}")
    public void processMessage(List<ProductItemDto> productItemDtoList,
                               Channel channel,
                               @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        // process the received product
        log.info("Processing RabbitMQ message");
        channel.basicAck(deliveryTag, false);
        productService.decrementStock(productItemDtoList);
    }

}
