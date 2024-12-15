package com.ecommerce.consumer;

import java.math.BigDecimal;

public class Item {
    private String itemId;
    private int quantity;
    private double price;

    // Getter for itemId
    public String getItemId() {
        return itemId;
    }

    // Setter for itemId
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;
    }
}
