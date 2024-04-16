package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    @Query("SELECT p.id FROM Post p WHERE p.placeId = :placeId")
    List<Long> findByPlaceId(Long placeId);

    @Query("SELECT p.id FROM Post p WHERE p.createdBy = :userId")
    List<Long> findByUserId(Long userId);

    @Query("SELECT p.id FROM Post p ORDER BY p.createdAt")
    List<Long> findAllOrderByCreatAt();
    @Query("SELECT count(p) FROM Post p WHERE p.placeId = :placeId")
    Long countByPlaceId(Long placeId);

}
