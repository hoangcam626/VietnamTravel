package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.relationship.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceImageRepo extends JpaRepository<PlaceImage, Long> {
    @Query("SELECT pi.imageId FROM PlaceImage pi Where pi.placeId =: placeId")
    List<Long> findImagIdeByPlaceId(Long placeId);
    @Query("SELECT count(pi)>0 FROM PlaceImage pi WHERE pi.imageId =:imageId")
    Boolean existsByImageId(Long imageId);
}
