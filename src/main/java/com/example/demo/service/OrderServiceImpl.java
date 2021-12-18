package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersInRange(Date startDate, Date endDate) {
        return this.orderRepository.findByOrderDateGreaterThanAndOrderDateLessThan(startDate, endDate);
    }

    @Override
    public Order save(Order order) {
        return this.orderRepository.save(order);
    }
}
