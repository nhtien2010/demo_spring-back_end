package com.example.demo.repositories;

import com.example.demo.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN FETCH o.shoppingProducts WHERE o.userModel.id = (:id)")
    Optional<List<Order>> findListOrderByUserModelIdAndFetchShoppingProducts(@Param("id") Long id);

}
