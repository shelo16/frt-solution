package com.frt.order.service.impl.rmqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductMessageSender {

    private final AmqpTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public ProductMessageSender(AmqpTemplate rabbitTemplate,
                                @Value("${rabbitmq.product-exchange-name}") String exchangeName,
                                @Value("${rabbitmq.product-routing-key}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void send(String msg) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
    }

}
