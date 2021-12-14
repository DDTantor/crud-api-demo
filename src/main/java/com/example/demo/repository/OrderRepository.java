package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository  extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(long orderId);
    List<Order> findByOrderDateGreaterThanAndOrderDateLessThan(Date startDate, Date endDate);
    List<Order> findByUserEmail(String userEmail);
}
