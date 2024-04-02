package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCommentRepo extends JpaRepository<SubComment, Long> {
    @Query("SELECT sc FROM SubComment sc WHERE sc.commentId = :commentId ORDER BY sc.createdAt")
    List<SubComment> findByCommentId(Long commentId);
}
