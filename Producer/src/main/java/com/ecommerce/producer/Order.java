package com.ecommerce.producer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    @NotBlank(message = "Order ID cannot be blank")
    private String orderId;

    @NotBlank(message = "Customer ID cannot be blank")
    private String customerId;

    @NotNull(message = "Order date cannot be null")
    private OffsetDateTime orderDate; // Keep @NotNull since this is not a primitive

    @NotEmpty(message = "Order must have at least one item")
    private List<Item> items;

    @Positive(message = "Total amount must be positive")
    private double totalAmount; // Removed @NotNull since double can't be null

    @NotBlank(message = "Currency cannot be blank")
    private String currency;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @PositiveOrZero(message = "Shipping cost must be zero or positive")
    private double shippingCost;

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

    public String getOrderId() {
        return orderId;
    }



}

