package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikedPlace extends JpaRepository<LikedPlace, Long> {

    @Query("SELECT lp FROM  LikedPlace lp WHERE lp.placeId = :placeId")
    List<LikedPlace> findByPlaceId(Long placeId);

    @Query("SELECT lp FROM  LikedPlace lp WHERE lp.placeId = :userId")
    List<LikedPlace> findByUserID(Long userId);

    @Query("SELECT count(lp) FROM  LikedPlace lp WHERE lp.placeId = :placeId")
    int countAllByUserId(Long userId);

    @Query("SELECT count(lp) FROM  LikedPlace lp WHERE lp.placeId = :userId")
    int countAllByPlaceId(Long placeId);
}
