package com.ecommerce.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Item {
    private String itemId;
    private int quantity;
    private double price;

    // Getters and setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}

