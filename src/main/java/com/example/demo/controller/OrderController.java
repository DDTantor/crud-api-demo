package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/orders/all")
    public ResponseEntity<List<Order>> getAllOrders()
    {
        try
        {
            List<Order> orders = new ArrayList<>();
            orderRepository.findAll().forEach(orders::add);
            if (orders.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersInRange(@RequestParam Date startDate, @RequestParam Date endDate)
    {
        try
        {
            List<Order> orders = new ArrayList<>();
            orderRepository.findByOrderDateGreaterThanAndOrderDateLessThan(startDate, endDate).forEach(orders::add);
            if (orders.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order)
    {
        try
        {
            Order newOrder = orderRepository.save(new Order(order.getUserEmail(), order.getOrderDate(), order.getProductList()));
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
