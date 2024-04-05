package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.relationship.LikePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikePlaceRepo extends JpaRepository<LikePlace, Long> {

    @Query("SELECT lp FROM  LikePlace lp WHERE lp.userID = :userId ORDER BY lp.createdAt")
    List<LikePlace> findByUserID(Long userId);

    @Query("SELECT count(lp) FROM  LikePlace lp WHERE lp.userID = :userId")
    int countLikeByUserId(Long userId);

    @Query("SELECT lp FROM  LikePlace lp WHERE lp.placeId = :placeId ORDER BY lp.createdAt")
    List<LikePlace> findByPlaceId(Long placeId);

    @Query("SELECT count(lp) FROM  LikePlace lp WHERE lp.placeId = :placeId")
    int countLikeByPlaceId(Long placeId);

    Boolean existsByUserIDAndPlaceId(Long userId, Long placeId);
    LikePlace findByUserIDAndPlaceId(Long userId, Long placeId);
}
