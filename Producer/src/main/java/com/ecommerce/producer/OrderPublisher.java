package com.ecommerce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

    private static final Logger logger = LoggerFactory.getLogger(OrderPublisher.class);
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(Order order)  {
        try {
            // Convert Order object to JSON (use Jackson)
            String orderJson = objectMapper.writeValueAsString(order);
            // Publish to RabbitMQ
            rabbitTemplate.convertAndSend("order-exchange", "order.created", orderJson);
            logger.info("Order published: {}", orderJson);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing order object to JSON", e);
        }
    }
}

