package com.example.demo.service;

import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderItemRepository;

public class OrderItemImpl implements OrderItemService{
    private OrderItemRepository orderItemRepository;

    public OrderItemImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return this.orderItemRepository.save(orderItem);
    }
}
