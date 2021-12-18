package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "userEmail")
    private String userEmail;

    @JsonFormat(pattern = "MM/dd/yyyy")
    @Column(name = "create_date")
    private Date orderDate;

    @Column(name = "price")
    private double orderPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pk.order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setOrderItemsAndOrderPrice(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.orderPrice = orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }
}
