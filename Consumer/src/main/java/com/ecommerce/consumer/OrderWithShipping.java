package com.ecommerce.consumer;

public class OrderWithShipping {
    private final Order order;
    private final double shippingCost;

    public OrderWithShipping(Order order, double shippingCost) {
        this.order = order;
        this.shippingCost = shippingCost;
    }

    public Order getOrder() {
        return order;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    @Override
    public String toString() {
        return "OrderWithShipping{" +
                "order=" + order +
                ", shippingCost=" + shippingCost +
                '}';
    }
}
