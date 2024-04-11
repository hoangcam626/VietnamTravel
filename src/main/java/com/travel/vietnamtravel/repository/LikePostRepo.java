package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.relationship.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikePostRepo extends JpaRepository<LikePost, Long> {

    @Query("SELECT l FROM  LikePost l WHERE l.userID = :userId ORDER BY l.createdAt DESC")
    List<LikePost> findByUserID(Long userId);

    @Query("SELECT count(l) FROM  LikePost l WHERE l.userID = :userId")
    int countLikeByUserId(Long userId);

    @Query("SELECT l FROM  LikePost l WHERE l.postId = :postId ORDER BY l.createdAt DESC")
    List<LikePost> findByPlaceId(Long postId);

    @Query("SELECT count(l) FROM  LikePost l WHERE l.postId = :postId")
    Long countLikeByPlaceId(Long postId);

    Boolean existsByUserIDAndPostId(Long userId, Long postId);

    LikePost findByUserIDAndPostId(Long userId, Long postId);
}
