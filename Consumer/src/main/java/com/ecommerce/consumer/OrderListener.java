package com.ecommerce.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OrderListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);

    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;

    @Value("${consumer.order.queue}")
    private String orderQueue;

    public OrderListener(ObjectMapper objectMapper, OrderRepository orderRepository) {
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = "#{@orderQueue}")
    public void processOrder(String orderJson) {
        try {
            // Deserialize the JSON message into an Order object
            Order order = objectMapper.readValue(orderJson, Order.class);

            // Only process orders where status is "new"
            if (!"new".equalsIgnoreCase(order.getStatus())) {
                logger.info("Skipping order with status: {}", order.getStatus());
                return;
            }

            // Calculate shipping cost
            double shippingCost = order.getTotalAmount() * 0.02;

            // Log and store the order details
            logger.info("Received Order: {}", order);
            logger.info("Calculated Shipping Cost: {}", shippingCost);

            // Store the order in memory (or replace this with a database call)
            orderRepository.addOrder(order, shippingCost);

        } catch (Exception e) {
            logger.error("Failed to process order: {}", e.getMessage(), e);
        }
    }
}
