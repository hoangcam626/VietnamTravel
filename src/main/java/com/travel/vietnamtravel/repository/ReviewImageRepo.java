package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.relationship.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewImageRepo extends JpaRepository<ReviewImage, Long> {

    @Query("SELECT ri FROM ReviewImage ri WHERE ri.reviewId = :reviewId")
    List<ReviewImage> findByReviewId(Long reviewId);

    @Query("SELECT ri.imageId FROM ReviewImage ri WHERE ri.reviewId = :reviewId")
    List<Long> findImageByReviewId(Long reviewId);
}
