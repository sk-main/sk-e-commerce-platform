package com.ecommerce.consumer;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderRepository {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OrderRepository.class);

    private static final Map<String, OrderWithShipping> orderStorage = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public static Map<String, OrderWithShipping> getOrderStorage() {
        return orderStorage; // Add this method for debugging full storage
    }

    public static void addOrder(Order order, double shippingCost) {
        String normalizedOrderId = order.getOrderId().trim();
        logger.info("Adding order: {} with shipping cost: {}", normalizedOrderId, shippingCost);
        orderStorage.put(normalizedOrderId, new OrderWithShipping(order, shippingCost));
        logger.debug("Current order storage: {}", orderStorage);
    }

    public static OrderWithShipping getOrder(String orderId) {
        logger.debug("Retrieving orderId: {}. Current storage: {}", orderId, orderStorage);
        return orderStorage.get(orderId);
    }


    public Map<String, OrderWithShipping> getAllOrders() {
        return new ConcurrentHashMap<>(orderStorage); // Return a copy to avoid modifications
    }
}

