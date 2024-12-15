package com.ecommerce.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        return factory;
    }

    @Bean
    public Queue orderQueue() {
        // Use the same queue name as producer
        return new Queue("order-queue", true);
    }

    @Bean
    public TopicExchange exchange() {
        // Use the same exchange name as producer
        return new TopicExchange("order-exchange");
    }

    @Bean
    public Binding binding(Queue orderQueue, TopicExchange exchange) {
        // Use the same routing key as producer
        return BindingBuilder.bind(orderQueue).to(exchange).with("order.created");
    }
}
