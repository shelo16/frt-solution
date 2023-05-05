package com.frt.order.service.impl.rabbitmq;

import com.frt.order.model.notification.NotificationQueueDto;
import org.springframework.amqp.core.AmqpTemplate;

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

    public void send(NotificationQueueDto notificationQueueDto) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, notificationQueueDto);
    }

}
