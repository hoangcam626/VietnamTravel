package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findAllByUserId(Long userId);
    List<Review> findAllByPlaceId(Long placeId);

}
