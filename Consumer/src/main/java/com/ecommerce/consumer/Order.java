package com.ecommerce.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String orderId;
    private String customerId;
    private OffsetDateTime orderDate;
    private List<Item> items; // Add this field
    private double totalAmount;
    private String currency;
    private String status;
    private double shippingCost;// This will be calculated by the consumer

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OffsetDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(OffsetDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "orderId='" + orderId + '\'' +
//                ", customerId='" + customerId + '\'' +
//                ", orderDate='" + orderDate + '\'' +
//                ", status='" + status + '\'' +
//                ", totalAmount=" + totalAmount +
//                ", currency='" + currency + '\'' +
//                ", shippingCost=" + shippingCost +
//                '}';
//    }


}
