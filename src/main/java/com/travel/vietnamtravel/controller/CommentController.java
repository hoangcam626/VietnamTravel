package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.comment.sdi.*;
import com.travel.vietnamtravel.dto.comment.sdo.*;
import com.travel.vietnamtravel.dto.likecomment.sdi.*;
import com.travel.vietnamtravel.dto.likecomment.sdo.*;
import com.travel.vietnamtravel.service.CommentService;
import com.travel.vietnamtravel.service.LikeCommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    private final LikeCommentService likeCommentService;
    @PostMapping
    public ResponseEntity<CommentCreateSdo> create(@RequestBody CommentCreateSdi req){
        return ResponseEntity.ok(commentService.create(req));
    }
    @PutMapping
    public ResponseEntity<CommentUpdateSdo> update(@RequestBody CommentUpdateSdi req){
        return ResponseEntity.ok(commentService.update(req));
    }
    @DeleteMapping
    public ResponseEntity<CommentDeleteSdo> delete(@RequestBody CommentDeleteSdi req){
        return ResponseEntity.ok(commentService.delete(req));
    }

    @GetMapping
    public ResponseEntity<CommentSelfSdo> self(@RequestBody CommentSelfSdi req){
        return ResponseEntity.ok(commentService.self(req));
    }


    @GetMapping("/comments/create-by")
    public ResponseEntity<List<CommentSelfSdo>> createBy(@RequestBody CommentJoinUserSdi req){
        return ResponseEntity.ok(commentService.createBy(req));
    }

    @GetMapping("/comments/in-post")
    public ResponseEntity<List<CommentSelfSdo>> commentInPost(@RequestBody CommentJoinPostSdi req){
        return ResponseEntity.ok(commentService.commentsInPost(req));
    }

    @GetMapping("/comments/sub-comment")
    public ResponseEntity<List<CommentSelfSdo>> subComments(@RequestBody CommentSelfSdi req){
        return ResponseEntity.ok(commentService.subComments(req));
    }
    @PostMapping("/like")
    public ResponseEntity<LikeCommentCreateSdo> like(LikeCommentCreateSdi req){
        return ResponseEntity.ok(likeCommentService.like(req));
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<LikeCommentDeleteSdo> like(LikeCommentDeleteSdi req){
        return ResponseEntity.ok(likeCommentService.unlike(req));
    }

    @GetMapping("/favourites")
    public ResponseEntity<List<CommentSelfSdo>> favourites(LikeCommentJoinUserSdi req){
        return ResponseEntity.ok(likeCommentService.favorites(req));
    }

}
