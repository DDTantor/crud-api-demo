package com.example.demo.dto;

import java.util.List;

public class OrderDto {
    private String userEmail;
    private List<OrderItemDto> orderItems;

    public OrderDto() {
    }

    public OrderDto(String userEmail, List<OrderItemDto> orderItems) {
        this.userEmail = userEmail;
        this.orderItems = orderItems;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
