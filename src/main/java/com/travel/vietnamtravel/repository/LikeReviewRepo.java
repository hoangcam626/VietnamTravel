package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.relationship.LikeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeReviewRepo extends JpaRepository<LikeReview, Long> {

    @Query("SELECT l FROM  LikeReview l WHERE l.userID = :userId ORDER BY l.createdAt DESC")
    List<LikeReview> findByUserId(Long userId);

    @Query("SELECT count(l) FROM  LikeReview l WHERE l.userID = :userId")
    int countAllByLiked(Long userId);

    @Query("SELECT l FROM  LikeReview l WHERE l.reviewId = :reviewId ORDER BY l.createdAt DESC")
    List<LikeReview> findByReviewId(Long reviewId);

    @Query("SELECT count(l) FROM  LikeReview l WHERE l.reviewId = :reviewId")
    Long countAllByReviewId(Long reviewId);

    Boolean existsByUserIDAndReviewId(Long userID, Long reviewId);
    LikeReview findByUserIDAndReviewId(Long userId, Long reviewID);
}
