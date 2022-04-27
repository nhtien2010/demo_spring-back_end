package com.example.demo.repositories;

import com.example.demo.domains.Category;
import com.example.demo.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}
