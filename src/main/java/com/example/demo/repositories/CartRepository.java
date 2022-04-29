package com.example.demo.repositories;

import com.example.demo.domains.Cart;
import com.example.demo.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c JOIN FETCH c.shoppingProducts WHERE c.id = (:id)")
    Optional<Cart> findCartByIdAndFetchShoppingProducts(@Param("id") Long id);
    @Query("SELECT c FROM Cart c JOIN FETCH c.shoppingProducts WHERE c.userModel.id = (:id)")
    Optional<Cart> findCartByUserModelIdAndFetchShoppingProducts(@Param("id") Long id);
}
