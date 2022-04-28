package com.example.demo.repositories;

import com.example.demo.domains.Rating;
import com.example.demo.domains.UserModel;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Boolean existsRatingByUserModelIdAndProductId(Long userId, Long productId);
    Optional<List<Rating>> findAllByProductId(Long id);
}
