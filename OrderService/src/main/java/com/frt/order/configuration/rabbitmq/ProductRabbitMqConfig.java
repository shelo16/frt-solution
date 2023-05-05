package com.frt.order.configuration.rabbitmq;

import com.frt.order.service.impl.rabbitmq.ProductMessageSender;
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
public class ProductRabbitMqConfig {

    @Value("${rabbitmq.product-exchange-name}")
    private String exchangeName;

    @Value("${rabbitmq.product-queue-name}")
    private String queueName;

    @Value("${rabbitmq.product-routing-key}")
    private String routingKey;

    @Bean
    public ProductMessageSender productMessageSender(@Qualifier("product") AmqpTemplate rabbitTemplate) {
        return new ProductMessageSender(rabbitTemplate, exchangeName, routingKey);
    }

    @Bean
    public Queue productQueue() {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange productExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding productBinding(Queue productQueue, TopicExchange productExchange) {
        return BindingBuilder.bind(productQueue).to(productExchange).with(routingKey);
    }

    @Bean
    @Qualifier("product")
    public RabbitTemplate productRabbitTemplate(ConnectionFactory connectionFactory,
                                                MessageConverter productMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(productMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter productMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

