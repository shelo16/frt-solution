package com.frt.order.service.impl.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;

public class PadMessageSender {

    private final AmqpTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public PadMessageSender(AmqpTemplate padRabbitTemplate,
                            String exchangeName,
                            String routingKey) {
        this.rabbitTemplate = padRabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void send(Long orderId) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }

}
