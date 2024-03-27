package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Long> {
    List<Place> findAllByProvinceId(Long provinceId);
    List<Place> findAllByDistrictId(Long districtId);
    List<Place> findAllByWardId(Long wardId);
}
