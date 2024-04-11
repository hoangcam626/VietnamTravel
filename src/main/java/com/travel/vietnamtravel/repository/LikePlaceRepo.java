package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.relationship.LikePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikePlaceRepo extends JpaRepository<LikePlace, Long> {

    @Query("SELECT l FROM  LikePlace l WHERE l.userID = :userId ORDER BY l.createdAt DESC")
    List<LikePlace> findByUserID(Long userId);

    @Query("SELECT count(l) FROM  LikePlace l WHERE l.userID = :userId")
    int countLikeByUserId(Long userId);

    @Query("SELECT l FROM  LikePlace l WHERE l.placeId = :placeId ORDER BY l.createdAt DESC")
    List<LikePlace> findByPlaceId(Long placeId);

    @Query("SELECT count(l) FROM  LikePlace l WHERE l.placeId = :placeId")
    Long countLikeByPlaceId(Long placeId);

    Boolean existsByUserIDAndPlaceId(Long userId, Long placeId);
    LikePlace findByUserIDAndPlaceId(Long userId, Long placeId);
}
