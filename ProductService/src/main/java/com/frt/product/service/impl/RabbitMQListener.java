package com.frt.product.service.impl;

import com.frt.product.configuration.rabbitmq.RabbitMQConfig;
import com.frt.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQListener {

    private final ProductService productService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processMessage(String msg) {
        // process the received product
        log.info("Processing RabbitMQ message : " + msg);
        System.out.println("Received message: " + msg);
    }

}
