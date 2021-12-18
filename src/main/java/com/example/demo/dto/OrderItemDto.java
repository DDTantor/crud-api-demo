package com.example.demo.dto;

import com.example.demo.model.OrderItem;

public class OrderItemDto {
    private String name;
    private double price;
    private int quantity;
    private int orderId;
    private int productId;

    public OrderItemDto() {}

    public OrderItemDto(String name, double price, int quantity, int orderId, int productId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }
}
