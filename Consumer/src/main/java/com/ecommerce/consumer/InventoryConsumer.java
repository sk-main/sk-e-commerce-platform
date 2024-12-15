package com.ecommerce.consumer;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

public class InventoryConsumer {
//    private static final String EXCHANGE_NAME = "order_exchange";
//    private static final String QUEUE_NAME = "inventory_queue";
//
//    public static void main(String[] args) throws Exception {
//        // Set up RabbitMQ connection
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        // Declare the queue and bind it to the exchange
//        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
//
//        System.out.println("Waiting for messages...");
//
//        // Define the message handling logic
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//            System.out.println("Processing order in Inventory Service: " + message);
//            // Add inventory-specific processing logic here
//        };
//
//        // Start consuming messages
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
//    }
}
