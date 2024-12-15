package com.ecommerce.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class OrderProducer {
    private static final String EXCHANGE_NAME = "order_exchange";

    public static void main(String[] args) throws Exception {
        // Set up RabbitMQ connection
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declare the exchange
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            // Order details as JSON (example)
            String orderDetails = """
                {
                  "order_id": 1234,
                  "customer_id": 5678,
                  "items": [{"product_id": 101, "quantity": 2}],
                  "total_price": 49.99
                }
            """;

            // Publish the message
            channel.basicPublish(EXCHANGE_NAME, "", null, orderDetails.getBytes(StandardCharsets.UTF_8));
            System.out.println("Order broadcasted: " + orderDetails);
        }
    }
}
