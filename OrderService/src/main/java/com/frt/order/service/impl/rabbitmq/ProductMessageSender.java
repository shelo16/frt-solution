package com.frt.order.service.impl.rabbitmq;

import com.frt.order.model.product.ProductItemDto;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.List;

public class ProductMessageSender {

    private final AmqpTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public ProductMessageSender(AmqpTemplate productRabbitTemplate,
                                String exchangeName,
                                String routingKey) {
        this.rabbitTemplate = productRabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void send(List<ProductItemDto> productItemDtoList) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, productItemDtoList);
    }

}
