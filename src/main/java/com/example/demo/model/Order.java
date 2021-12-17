package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    @Column(name = "userEmail")
    private String userEmail;

    @JsonFormat(pattern = "MM/dd/yyyy")
    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "orderPrice")
    private double orderPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> productList = new ArrayList<>();


    public Order() {
    }

    public Order(String userEmail, Date orderDate, List<OrderItem> productList, double orderPrice) {
        this.userEmail = userEmail;
        this.orderDate = orderDate;
        this.productList = productList;
        this.orderPrice = orderPrice;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getProductList() {
        return productList;
    }
    public double getOrderPrice() {
        return orderPrice;
    }

}
