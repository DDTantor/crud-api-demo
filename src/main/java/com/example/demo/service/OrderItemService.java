package com.example.demo.service;

import com.example.demo.model.OrderItem;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderItemService {
    OrderItem save(OrderItem orderItem);
}
