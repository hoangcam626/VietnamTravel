package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query("SELECT c.id FROM Comment c WHERE c.postID =: postId ORDER BY c.createdAt")
    List<Long> findByPostID(Long postId);

    @Query("SELECT c.id FROM Comment c WHERE c.createdBy =: userId ORDER BY c.createdAt")
    List<Long> findByUserId(Long userId);
}