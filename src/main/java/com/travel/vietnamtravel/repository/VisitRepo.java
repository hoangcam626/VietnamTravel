package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.relationship.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitRepo extends JpaRepository<Visit, Long> {
    Boolean existsByUserIdAndPlaceId(Long userId, Long placeId);

    @Query("select count(v) from Visit  v WHERE v.userId=:userId")
    List<Visit> findByUserId(Long userId);

    @Query("select count(v) from Visit  v WHERE v.placeId=:placeId")
    Long countByPlaceId(Long placeId);
}
