package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.comment.sdi.*;
import com.travel.vietnamtravel.dto.comment.sdo.CommentCreateSdo;
import com.travel.vietnamtravel.dto.comment.sdo.CommentDeleteSdo;
import com.travel.vietnamtravel.dto.comment.sdo.CommentSelfSdo;
import com.travel.vietnamtravel.dto.comment.sdo.CommentUpdateSdo;
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
}
