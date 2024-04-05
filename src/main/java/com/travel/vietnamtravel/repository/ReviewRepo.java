package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<Review> findAllByUserId(Long userId);

    List<Review> findAllByPlaceId(Long placeId);

    @Query("SELECT sum(r.rating)/count(r) FROM Review r WHERE r.placeId =: placeId")
    Double ranking(Long placeId);

}
