package com.ecommerce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.producer.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "order-exchange";
    private static final String ROUTING_KEY = "order.created";

    private final OrderPublisher orderPublisher;

    @Autowired
    public OrderController(OrderPublisher orderPublisher) {
        this.orderPublisher = orderPublisher;
    }

    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {

        // Validate inputs based on the fields in the order object
        if (order.getCustomerId() == null || order.getCustomerId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        String status = order.getStatus();
        if (status == null || (!status.equals("new") && !status.equals("pending"))) {
            return ResponseEntity.badRequest().body(null);
        }

        // Validate orderDate
        OffsetDateTime providedDate = order.getOrderDate();
        if (providedDate == null || providedDate.isAfter(OffsetDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body(null); // Optionally, return meaningful error details
        }
        // Set validated orderDate (re-assigning ensures consistency even if necessary modifications are made)
        order.setOrderDate(providedDate);

        // Validate currency
        String providedCurrency = order.getCurrency();
        if (providedCurrency == null || providedCurrency.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(null); // Optionally, return meaningful error details
        }
        // Set validated currency
        order.setStatus(status);

        // Optionally normalize fields
        order.setOrderId(order.getOrderId().trim().toUpperCase());
        order.setCustomerId(order.getCustomerId().trim());
        order.setCurrency(order.getCurrency().trim().toUpperCase());

        // Recalculate totalAmount from the provided items
        double totalAmount = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);

        // Publish order to RabbitMQ using orderPublisher
        orderPublisher.publish(order);

        // Log the creation
        logger.info("Order created successfully: {}", order);

        return ResponseEntity.ok(order);
    }
}


