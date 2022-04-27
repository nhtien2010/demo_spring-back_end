package com.example.demo.repositories;

import com.example.demo.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}