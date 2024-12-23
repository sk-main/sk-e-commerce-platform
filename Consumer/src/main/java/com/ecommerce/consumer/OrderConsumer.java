package com.ecommerce.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ecommerce.consumer.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderConsumer {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OrderConsumer.class);

    // In-memory storage for orders (you could use a real database in a real-world app)
    private final Map<String, Order> orderDatabase = new HashMap<>();

    @RabbitListener(queues = "order-queue")
    public void consumeOrder(Order order) {
        if (order != null && "new".equals(order.getStatus())) {
            // Calculate shipping cost (2% of totalAmount)
            double shippingCost = 0.02 * order.getTotalAmount();
            order.setShippingCost(shippingCost);

            // Store the order and shipping cost in memory
            orderDatabase.put(order.getOrderId(), order);

            // Log the order details with shipping cost
            System.out.println("Order received: " + order);
        }
    }

    // Method to fetch order details and shipping cost
    public Order getOrderDetails(String orderId) {
        logger.debug("Fetching order details for orderId: {}. Current storage: {}", orderId, OrderRepository.getOrderStorage());

        // Get the OrderWithShipping object from the repository
        OrderWithShipping orderWithShipping = OrderRepository.getOrder(orderId);

        if (orderWithShipping == null) {
            return null; // or throw an exception based on your application logic
        }

        // Extract and return the Order object
        return orderWithShipping.getOrder();
    }


}
