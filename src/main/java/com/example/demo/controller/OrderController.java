package com.example.demo.controller;

import com.example.demo.dto.OrderItemDto;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OrderController {
    ProductService productService;
    OrderService orderService;
    OrderItemService orderItemService;

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
    public ResponseEntity<Order> createOrder(@RequestBody OrderForm orderForm) {
        try {
            List<OrderItemDto> formDtos = orderForm.getOrderItems();
            List<OrderItem> orderItems = new ArrayList<>();
            Order order = new Order();
            for (OrderItemDto dto : formDtos) {
                Product product = productService.getProductById(dto.getProductId()).get();
                orderItems.add(new OrderItem(order, product, dto.getQuantity()));
            }

            order.setOrderItemsAndOrderPrice(orderItems);
            order.setUserEmail(orderForm.getUserEmail());
            orderService.save(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static class OrderForm {
        private List<OrderItemDto> orderItems;
        private String userEmail;

        public List<OrderItemDto> getOrderItems() {
            return this.orderItems;
        }

        public String getUserEmail() {
            return this.userEmail;
        }
    }
}
