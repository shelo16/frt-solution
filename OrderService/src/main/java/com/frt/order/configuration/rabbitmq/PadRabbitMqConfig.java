package com.frt.order.configuration.rabbitmq;

import com.frt.order.service.impl.rabbitmq.PadMessageSender;
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
public class PadRabbitMqConfig {

    @Value("${rabbitmq.pad.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.pad.queue.name}")
    private String queueName;

    @Value("${rabbitmq.pad.routing.key}")
    private String routingKey;

    @Bean
    public PadMessageSender padMessageSender(@Qualifier("pad") AmqpTemplate rabbitTemplate) {
        return new PadMessageSender(rabbitTemplate, exchangeName, routingKey);
    }

    @Bean
    public Queue padQueue() {
        return new Queue(queueName);
    }

    @Bean
    public DirectExchange padExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding padBinding(Queue productQueue, DirectExchange productExchange) {
        return BindingBuilder.bind(productQueue).to(productExchange).with(routingKey);
    }

    @Bean
    @Qualifier("pad")
    public RabbitTemplate padRabbitTemplate(ConnectionFactory connectionFactory,
                                            MessageConverter productMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(productMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter padMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

