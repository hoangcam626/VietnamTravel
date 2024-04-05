package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.post.sdi.*;
import com.travel.vietnamtravel.dto.post.sdo.PostCreateSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostDeleteSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostSelfSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostUpdateSdo;
import com.travel.vietnamtravel.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostCreateSdo> create(@RequestBody PostCreateSdi req){
        return ResponseEntity.ok(postService.create(req));
    }
    @PutMapping
    public ResponseEntity<PostUpdateSdo> update(@RequestBody PostUpdateSdi req){
        return ResponseEntity.ok(postService.update(req));
    }
    @DeleteMapping
    public ResponseEntity<PostDeleteSdo> delete(@RequestBody PostDeleteSdi req){
        return ResponseEntity.ok(postService.delete(req));
    }

    @GetMapping
    public ResponseEntity<PostSelfSdo> self(@RequestBody PostSelfSdi req){
        return ResponseEntity.ok(postService.self(req));
    }

    @GetMapping("/posts/all")
    public ResponseEntity<List<PostSelfSdo>> allPosts(){
        return ResponseEntity.ok(postService.allPosts());
    }
    @GetMapping("/posts/create-by")
    public ResponseEntity<List<PostSelfSdo>> createBy(@RequestBody PostJoinUserSdi req){
        return ResponseEntity.ok(postService.createBy(req));
    }

    @GetMapping("/posts/in-place")
    public ResponseEntity<List<PostSelfSdo>> postsInPlace(@RequestBody PostJoinPlaceSdi req){
        return ResponseEntity.ok(postService.postsInPlace(req));
    }
}
