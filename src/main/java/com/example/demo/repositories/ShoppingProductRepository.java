package com.example.demo.repositories;

import com.example.demo.domains.ShoppingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Long> {
}
