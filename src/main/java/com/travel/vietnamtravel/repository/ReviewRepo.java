package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    @Query("SELECT r.id FROM Review r WHERE r.createdBy = :userId ORDER BY r.createdAt DESC  ")
    List<Long> findAllByUserId(Long userId);

    @Query("SELECT r.id FROM Review r WHERE r.placeId = :placeId ORDER BY r.createdAt DESC ")
    List<Long> findAllByPlaceId(Long placeId);


    @Query("SELECT count(r) FROM Review r WHERE r.placeId = :placeId ORDER BY r.createdAt DESC ")
    Long countReviewByPlaceId(Long placeId);

    @Query("SELECT count(r) FROM Review r WHERE r.placeId = :placeId and r.rating = :rating")
    Long countRating(Long placeId, int rating);
    @Query("SELECT CASE WHEN count(r) > 0 THEN sum(r.rating)/count(r) ELSE 0 END FROM Review r WHERE r.placeId = :placeId")
    Double rating(Long placeId);

    @Query("SELECT r.id FROM Review r ORDER BY r.createdAt DESC ")
    List<Long> getAll();

}
