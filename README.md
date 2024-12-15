# e-commerce-platform
a backend system for an e-commerce platform

## 1. Full Name and ID Number
**Name:** Shalev Kedar  
**ID:** 314714080

---

## 2. Full URLs and API Requests

### **Producer Application**
- **Base URL:** `http://localhost:8081`
- **Endpoint to Create an Order:**
    - URL: `http://localhost:8081/api/orders/create-order`
    - HTTP Method: `POST`
    - Body:
      ```json
      {
          "orderId": "ORD-12345",
          "customerId": "CUST-67890",
          "orderDate": "2024-10-19T10:45:00Z",
          "items": [
              { "itemId": "ITEM-001", "quantity": 2, "price": 19.99 },
              { "itemId": "ITEM-002", "quantity": 1, "price": 39.99 }
          ],
          "totalAmount": 79.97,
          "currency": "USD",
          "status": "new"
      }
      ```

### **Consumer Application**
- **Base URL:** `http://localhost:8082`
- **Endpoint to Fetch Order Details:**
    - URL: `http://localhost:8082/api/orders/order-details`
    - HTTP Method: `GET`
    - Query Parameter: `orderId`

Example:  
`http://localhost:8082/api/orders/order-details?orderId=ORD-12345`

---

## 3. What type of exchange did you choose and why?

### **Type:** Topic Exchange
**Reason:** A topic exchange allows flexible routing using binding keys, making it ideal for scenarios where multiple consumers may listen to specific subsets of messages. In this project, the binding key ensures that only messages with a specific routing key are processed by the consumer.

---

## 4. Is there a binding key on the consumer? If so, what is it and why?

### **Binding Key:** `order.created`
**Reason:** The binding key ensures that the consumer only processes messages related to new order events. This reduces unnecessary message consumption and aligns with the business logic of the application.

---

## 5. Which service declared the exchange and queue and why?

### **Service:** Producer
**Reason:** The producer declares the exchange and queue to ensure that the message broker is properly configured before publishing messages. This approach centralizes message broker configuration, ensuring that consumers can connect and start listening without having to initialize these components.
