package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.model.Order;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OrderController {
    ProductService productService;
    OrderService orderService;
    OrderItemService orderItemService;

    public OrderController(ProductService productService, OrderService orderService, OrderItemService orderItemService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("/api/orders/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/orders")
    public ResponseEntity<List<Order>> getOrdersInRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        try {
            List<Order> orders = orderService.getAllOrdersInRange(startDate, endDate);
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {
        try {
            Order order = orderService.create(orderDto);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
