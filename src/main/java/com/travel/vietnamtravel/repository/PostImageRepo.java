package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostImageRepo extends JpaRepository<PostImage, Long> {

    @Query("SELECT pi.id FROM PostImage pi WHERE pi.placeId =: placeId")
    List<Long> findByPlaceId(Long placeId);
}
