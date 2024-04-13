package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.likepost.sdi.*;
import com.travel.vietnamtravel.dto.likepost.sdo.*;
import com.travel.vietnamtravel.dto.post.sdi.*;
import com.travel.vietnamtravel.dto.post.sdo.*;
import com.travel.vietnamtravel.service.LikePostService;
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
    private final LikePostService likePostService;
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

    @PostMapping("/like")
    public ResponseEntity<LikePostCreateSdo> like(LikePostCreateSdi req){
        return ResponseEntity.ok(likePostService.like(req));
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<LikePostDeleteSdo> like(LikePostDeleteSdi req){
        return ResponseEntity.ok(likePostService.unlike(req));
    }

    @GetMapping("/favourites")
    public ResponseEntity<List<PostSelfSdo>> favourites(LikePostJoinUserSdi req){
        return ResponseEntity.ok(likePostService.favorites(req));
    }
}
