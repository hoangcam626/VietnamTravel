package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Long> {
    @Query("select p.id from Place p WHERE p.provinceCode = :provinceCode")
    List<Long> findAllByProvinceCode(String provinceCode);

    @Query("select p.id from Place p WHERE p.districtCode = :districtCode")
    List<Long> findAllByDistrictCode(String districtCode);

    @Query("select p.id from Place p WHERE p.wardCode = :wardCode")
    List<Long> findAllByWardCode(String wardCod);

    @Query("select p.id from Place p")
    List<Long> findAllIds();
    @Query("select p.id from Place p left join Province pr ON pr.code = p.provinceCode " +
            "left join District  d on d.code = p.districtCode " +
            "left join Ward w on w.code = p.wardCode " +
            "where p.name like %:keyword% or pr.name like %:keyword% or d.name like %:keyword or w.name like %:keyword%")
    List<Long> searchPlace(String keyword);

    @Query("select count(p) from Place p inner join Post ps on ps.placeId = p.id where p.id =:id")
    Long countPost(Long id);

    @Query("select count(p)>0 from Place p inner join Post ps on ps.placeId = p.id where p.id =:id and ps.createdBy =:userId")
    Boolean hasPost(Long id, Long userId);

    @Query("select count(p) from Place p inner join Review r on r.placeId = p.id where p.id =:id")
    Long countReview(Long id);

    @Query("select count(p)>0 from Place p inner join Review r on r.placeId = p.id where p.id =:id and r.createdBy =:userId")
    Boolean hasReview(Long id, Long userId);
}
