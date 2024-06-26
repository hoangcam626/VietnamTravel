package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    @Query("SELECT p.id FROM Post p WHERE p.placeId = :placeId ORDER BY p.createdAt DESC")
    List<Long> findByPlaceId(Long placeId);

    @Query("SELECT p.id FROM Post p WHERE p.createdBy = :userId ORDER BY p.createdAt DESC")
    List<Long> findByUserId(Long userId);

    @Query("SELECT p.id FROM Post p ORDER BY p.createdAt DESC ")
    List<Long> findAllOrderByCreatAt();
    @Query("SELECT count(p) FROM Post p WHERE p.placeId = :placeId")
    Long countByPlaceId(Long placeId);

    @Query("select ps.id from Post ps inner join Place p on p.id = ps.placeId " +
            "left join Province pr ON pr.code = p.provinceCode " +
            "left join District  d on d.code = p.districtCode " +
            "left join Ward w on w.code = p.wardCode " +
            "where p.name like %:keyword% " +
            "or pr.name like %:keyword% " +
            "or d.name like %:keyword " +
            "or w.name like %:keyword%")
    List<Long> searchPost(String keyword);
}
