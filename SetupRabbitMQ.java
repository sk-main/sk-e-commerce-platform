import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQSetup {
    private static final String EXCHANGE_NAME = "order_exchange";
    private static final String INVENTORY_QUEUE = "inventory_queue";
    private static final String BILLING_QUEUE = "billing_queue";
    private static final String SHIPPING_QUEUE = "shipping_queue";

    public static void main(String[] args) throws Exception {
        // Step 1: Connect to RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Change to RabbitMQ server host
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Step 2: Declare a fanout exchange
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            // Step 3: Declare queues
            channel.queueDeclare(INVENTORY_QUEUE, false, false, false, null);
            channel.queueDeclare(BILLING_QUEUE, false, false, false, null);
            channel.queueDeclare(SHIPPING_QUEUE, false, false, false, null);

            // Step 4: Bind queues to the exchange
            channel.queueBind(INVENTORY_QUEUE, EXCHANGE_NAME, "");
            channel.queueBind(BILLING_QUEUE, EXCHANGE_NAME, "");
            channel.queueBind(SHIPPING_QUEUE, EXCHANGE_NAME, "");

            System.out.println("RabbitMQ setup completed successfully.");
        }
    }
}
