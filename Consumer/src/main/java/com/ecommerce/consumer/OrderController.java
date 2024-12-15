package com.ecommerce.consumer;

import com.ecommerce.consumer.Order;
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

    @Autowired
    private OrderConsumer orderConsumer;

    public OrderController(OrderConsumer orderConsumer) {
        this.orderConsumer = orderConsumer;
    }

    @GetMapping("/order-details")
    public ResponseEntity<?> getOrderDetails(@RequestParam String orderId) {
        Order order = orderConsumer.getOrderDetails(orderId);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Order not found", "orderId", orderId));
        }
        return ResponseEntity.ok(order);
    }
}
