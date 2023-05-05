package com.frt.order.configuration.rabbitmq;

import com.frt.order.service.impl.rabbitmq.NotificationMessageSender;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationRabbitMqConfig {

    @Value("${rabbitmq.notification-exchange-name}")
    private String exchangeName;

    @Value("${rabbitmq.notification-queue-name}")
    private String queueName;

    @Value("${rabbitmq.notification-routing-key}")
    private String routingKey;

    @Bean
    public NotificationMessageSender notificationMessageSender(@Qualifier("notification") AmqpTemplate rabbitTemplate) {
        return new NotificationMessageSender(rabbitTemplate, exchangeName, routingKey);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationQueue).to(notificationExchange).with(routingKey);
    }

    @Bean
    @Qualifier("notification")
    public RabbitTemplate notificationRabbitTemplate(ConnectionFactory connectionFactory,
                                                     MessageConverter notificationMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(notificationMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter notificationMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

