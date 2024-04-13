package com.travel.vietnamtravel.repository;

import com.travel.vietnamtravel.entity.relationship.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeCommentRepo extends JpaRepository<LikeComment, Long> {

    @Query("SELECT lp FROM  LikeComment lp WHERE lp.userID = :userId ORDER BY lp.createdAt DESC")
    List<LikeComment> findByUserID(Long userId);

    @Query("SELECT count(lp) FROM  LikeComment lp WHERE lp.userID = :userId")
    int countLikeByUserId(Long userId);

    @Query("SELECT lp FROM  LikeComment lp WHERE lp.commentId = :commentId ORDER BY lp.createdAt DESC")
    List<LikeComment> findByCommentId(Long commentId);

    @Query("SELECT count(lp) FROM  LikeComment lp WHERE lp.commentId = :commentId")
    Long countLikeByCommentId(Long commentId);

    Boolean existsByUserIDAndCommentId(Long userId, Long commentId);

    LikeComment findByUserIDAndCommentId(Long userId, Long commentId);
}
