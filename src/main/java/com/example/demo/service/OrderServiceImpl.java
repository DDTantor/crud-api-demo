package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderItemDto;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    private OrderItemService orderItemService;
    private ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.productService = productService;
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
    public Order create(OrderDto orderDto) {
        Order order = new Order();
        Collection<String> orderItemNames = orderDto.getOrderItems().stream().map(OrderItemDto::getName).collect(Collectors.toCollection(TreeSet::new));
        List<Product> productList = productService.getProductsByNameIn(orderItemNames);
        List<OrderItemDto> orderItemDtoList = orderDto.getOrderItems();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i = 0; i < productList.size(); ++i) {
            orderItemList.add(new OrderItem(order, productList.get(i), orderItemDtoList.get(i).getQuantity()));
        }

        order.setOrderItemsAndOrderPrice(orderItemList);
        order.setUserEmail(orderDto.getUserEmail());
        return orderRepository.save(order);
    }
}
