package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.model.Order;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Validated
public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getAllOrdersInRange(Date startDate, Date endDate);
    Order create(OrderDto dto);
}
