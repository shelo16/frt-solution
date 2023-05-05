package com.frt.order.service.impl.rabbitmq;

import com.frt.order.model.ProductItemDto;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.List;

public class NotificationMessageSender {

    private final AmqpTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public NotificationMessageSender(AmqpTemplate notificationRabbitTemplate,
                                     String exchangeName,
                                     String routingKey) {
        this.rabbitTemplate = notificationRabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void send(List<ProductItemDto> productItemDtoList) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, productItemDtoList);
    }

}
