package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @EmbeddedId
    @JsonIgnore
    private OrderItemPK pk;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_price")
    private double productPrice;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, int quantity) {
        pk = new OrderItemPK();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = quantity;
        this.productPrice = product.getPrice();
    }

    public double getTotalPrice() {
        return this.productPrice * this.quantity;
    }

    public String getProductName() {
        return pk.getProduct().getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }
}
