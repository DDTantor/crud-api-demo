package com.example.demo.model;

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

    @Column(name = "email")
    private String userEmail;

    @Column(name = "date")
    private Date orderDate;

    @ManyToMany(mappedBy = "orderList")
    private List<Product> productList = new ArrayList<>();

    public Order() {
    }

    public Order(String userEmail, Date orderDate, List<Product> productList) {
        this.userEmail = userEmail;
        this.orderDate = orderDate;
        this.productList = productList;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
