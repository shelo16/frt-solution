package com.frt.pad.service.impl;

import com.frt.pad.service.OrderDeliveryService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQListener {

    private final OrderDeliveryService orderDeliveryService;

    @RabbitListener(queues = "${rabbitmq.pad.queue.name}")
    public void processMessage(Long orderId,
                               Channel channel,
                               @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {

        // process the received message
        log.info("Processing RabbitMQ message");
        channel.basicAck(deliveryTag, false);
        orderDeliveryService.createOrderDelivery(orderId);
    }

}
