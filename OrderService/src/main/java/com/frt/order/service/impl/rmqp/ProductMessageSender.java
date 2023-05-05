package com.frt.order.service.impl.rmqp;

import com.frt.order.model.ProductItemDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void send(List<ProductItemDto> productItemDtoList) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, productItemDtoList);
    }

}
