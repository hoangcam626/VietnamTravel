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
    @PostMapping("/create")
    public ResponseEntity<CommentCreateSdo> create( CommentCreateSdi req){
        return ResponseEntity.ok(commentService.create(req));
    }
    @PutMapping("/update")
    public ResponseEntity<CommentUpdateSdo> update( CommentUpdateSdi req){
        return ResponseEntity.ok(commentService.update(req));
    }
    @PostMapping("/delete")
    public ResponseEntity<CommentDeleteSdo> delete( CommentDeleteSdi req){
        return ResponseEntity.ok(commentService.delete(req));
    }

    @PostMapping("/self")
    public ResponseEntity<CommentSelfSdo> self( CommentSelfSdi req){
        return ResponseEntity.ok(commentService.self(req));
    }


    @PostMapping("/comments/create-by")
    public ResponseEntity<List<CommentSelfSdo>> createBy( CommentJoinUserSdi req){
        return ResponseEntity.ok(commentService.createBy(req));
    }

    @PostMapping("/comments/in-post")
    public ResponseEntity<List<CommentSelfSdo>> commentInPost( CommentJoinPostSdi req){
        return ResponseEntity.ok(commentService.commentsInPost(req));
    }

    @PostMapping("/comments/sub-comment")
    public ResponseEntity<List<CommentSelfSdo>> subComments( CommentSelfSdi req){
        return ResponseEntity.ok(commentService.subComments(req));
    }
    @PostMapping("/like")
    public ResponseEntity<LikeCommentCreateSdo> like(LikeCommentCreateSdi req){
        return ResponseEntity.ok(commentService.like(req));
    }

    @PostMapping("/unlike")
    public ResponseEntity<LikeCommentDeleteSdo> like(LikeCommentDeleteSdi req){
        return ResponseEntity.ok(commentService.unlike(req));
    }

    @PostMapping("/favourites")
    public ResponseEntity<List<CommentSelfSdo>> favourites(LikeCommentJoinUserSdi req){
        return ResponseEntity.ok(commentService.favorites(req));
    }

}
