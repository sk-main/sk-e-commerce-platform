package com.ecommerce.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Item {
    private String itemId;
    //private String itemName;
    private int quantity;
    private double price;

//    // Constructor
//    public Item(String itemId, String itemName, int quantity) {
//        this.itemId = itemId;
//        this.itemName = itemName;
//        this.quantity = quantity;
//    }

//    public static List<Item> generateRandomItems(int num) {
//        List<Item> items = new ArrayList<>();
//        Random rand = new Random();
//
//        for (int i = 0; i < num; i++) {
//            String itemId = "item" + (i + 1);
//            String itemName = "Item " + (i + 1);
//            int quantity = rand.nextInt(10) + 1; // Random quantity between 1 and 10
//            items.add(new Item(itemId, itemName, quantity));
//        }
//
//        return items;
//    }

    // Getters and setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

//    public String getItemName() {
//        return itemName;
//    }

//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }

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

