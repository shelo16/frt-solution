package com.frt.notification.service.impl;

import com.frt.notification.model.NotificationQueueDto;
import com.frt.notification.service.NotificationFacade;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQListener {

    private final NotificationFacade notificationFacade;

    @RabbitListener(queues = "${rabbitmq.notification-queue-name}")
    public void processMessage(NotificationQueueDto notificationQueueDto,
                               Channel channel,
                               @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {

        // process the received message
        log.info("Processing RabbitMQ message");
        channel.basicAck(deliveryTag, false);
        notificationFacade.processQueueMessage(notificationQueueDto);
    }

}
