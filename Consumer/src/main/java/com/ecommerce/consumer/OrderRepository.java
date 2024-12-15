package com.ecommerce.consumer;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderRepository {
    private static final Map<String, OrderWithShipping> orderStorage = new HashMap<>();

    public static void addOrder(Order order, double shippingCost) {
        orderStorage.put(order.getOrderId(), new OrderWithShipping(order, shippingCost));
    }

    public static OrderWithShipping getOrder(String orderId) {
        return orderStorage.get(orderId);
    }
}

class OrderWithShipping {
    private final Order order;
    private final double shippingCost;

    public OrderWithShipping(Order order, double shippingCost) {
        this.order = order;
        this.shippingCost = shippingCost;
    }

    public Order getOrder() {
        return order;
    }

    public double getShippingCost() {
        return shippingCost;
    }
}
