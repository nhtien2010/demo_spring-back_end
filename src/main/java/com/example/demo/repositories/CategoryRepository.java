package com.example.demo.repositories;

import com.example.demo.domains.Category;
import com.example.demo.domains.Order;
import com.example.demo.domains.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findByName(String name);
}
