package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.comment.sdi.*;
import com.travel.vietnamtravel.dto.comment.sdo.*;
import com.travel.vietnamtravel.dto.likecomment.sdi.*;
import com.travel.vietnamtravel.dto.likecomment.sdo.*;
import com.travel.vietnamtravel.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    public ResponseEntity<CommentCreateSdo> create( CommentCreateSdi req){
        return ResponseEntity.ok(commentService.create(req));
    }
    @PutMapping
    public ResponseEntity<CommentUpdateSdo> update( CommentUpdateSdi req){
        return ResponseEntity.ok(commentService.update(req));
    }
    @DeleteMapping
    public ResponseEntity<CommentDeleteSdo> delete( CommentDeleteSdi req){
        return ResponseEntity.ok(commentService.delete(req));
    }

    @GetMapping
    public ResponseEntity<CommentSelfSdo> self( CommentSelfSdi req){
        return ResponseEntity.ok(commentService.self(req));
    }


    @GetMapping("/comments/create-by")
    public ResponseEntity<List<CommentSelfSdo>> createBy( CommentJoinUserSdi req){
        return ResponseEntity.ok(commentService.createBy(req));
    }

    @GetMapping("/comments/in-post")
    public ResponseEntity<List<CommentSelfSdo>> commentInPost( CommentJoinPostSdi req){
        return ResponseEntity.ok(commentService.commentsInPost(req));
    }

    @GetMapping("/comments/sub-comment")
    public ResponseEntity<List<CommentSelfSdo>> subComments( CommentSelfSdi req){
        return ResponseEntity.ok(commentService.subComments(req));
    }
    @PostMapping("/like")
    public ResponseEntity<LikeCommentCreateSdo> like(LikeCommentCreateSdi req){
        return ResponseEntity.ok(commentService.like(req));
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<LikeCommentDeleteSdo> like(LikeCommentDeleteSdi req){
        return ResponseEntity.ok(commentService.unlike(req));
    }

    @GetMapping("/favourites")
    public ResponseEntity<List<CommentSelfSdo>> favourites(LikeCommentJoinUserSdi req){
        return ResponseEntity.ok(commentService.favorites(req));
    }

}
