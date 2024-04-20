package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Long> {
    List<Place> findAllByProvinceCode(String provinceCode);

    List<Place> findAllByDistrictCode(String districtCode);

    List<Place> findAllByWardCode(String wardCod);

    @Query("select p.id from Place p left join Province pr ON pr.code = p.provinceCode " +
            "left join District  d on d.code = p.districtCode " +
            "left join Ward w on w.code = p.wardCode " +
            "where p.name like %:keyword% or pr.name like %:keyword% or d.name like %:keyword or w.name like %:keyword%")
    List<Long> searchPlace(String keyword);
}
