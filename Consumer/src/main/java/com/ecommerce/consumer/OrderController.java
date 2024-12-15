package com.ecommerce.consumer;

import com.ecommerce.consumer.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderConsumer orderConsumer;

    public OrderController(OrderConsumer orderConsumer) {
        this.orderConsumer = orderConsumer;
    }

    @GetMapping("/order-details")
    public ResponseEntity<?> getOrderDetails(@RequestParam String orderId) {
        try {
            logger.info("Fetching order details for orderId: {}", orderId);
            // Normalize the input orderId
            String normalizedOrderId = normalizeOrderId(orderId);

            // Retrieve the order from the repository
            OrderWithShipping orderWithShipping = OrderRepository.getOrder(normalizedOrderId);

            //Order order = orderConsumer.getOrderDetails(orderId);


            if (orderWithShipping == null) {
                logger.warn("Order not found for orderId: {}", orderId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Order not found", "orderId", orderId));
            }

            Order order = orderWithShipping.getOrder();
            order.setShippingCost(orderWithShipping.getShippingCost());

            logger.info("Order details found: {}", orderWithShipping);
            return ResponseEntity.ok(orderWithShipping);
        } catch (Exception e) {
            logger.error("Error fetching order details for orderId: {}", orderId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An error occurred while fetching order details", "orderId", orderId));
        }
    }

    private String normalizeOrderId(String orderId) {
        return orderId == null ? null : orderId.trim();
    }

}
