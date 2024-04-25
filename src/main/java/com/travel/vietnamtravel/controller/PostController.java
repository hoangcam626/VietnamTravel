package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.likepost.sdi.*;
import com.travel.vietnamtravel.dto.likepost.sdo.*;
import com.travel.vietnamtravel.dto.post.sdi.*;
import com.travel.vietnamtravel.dto.post.sdo.*;
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

    @PostMapping("/create")
    public ResponseEntity<PostCreateSdo> create(PostCreateSdi req) {
        return ResponseEntity.ok(postService.create(req));
    }

    @PutMapping("/update")
    public ResponseEntity<PostUpdateSdo> update(PostUpdateSdi req) {
        return ResponseEntity.ok(postService.update(req));
    }

    @PostMapping("/delete")
    public ResponseEntity<PostDeleteSdo> delete(PostDeleteSdi req) {
        return ResponseEntity.ok(postService.delete(req));
    }

    @PostMapping("/self")
    public ResponseEntity<PostSelfSdo> self(PostSelfSdi req) {
        return ResponseEntity.ok(postService.self(req));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostSelfSdo>> allPosts() {
        return ResponseEntity.ok(postService.allPosts());
    }

    @PostMapping("/posts/create-by")
    public ResponseEntity<List<PostSelfSdo>> createBy(PostJoinUserSdi req) {
        return ResponseEntity.ok(postService.createBy(req));
    }

    @PostMapping("/posts/in-place")
    public ResponseEntity<List<PostSelfSdo>> postsInPlace(PostJoinPlaceSdi req) {
        return ResponseEntity.ok(postService.postsInPlace(req));
    }

    @PostMapping("/like")
    public ResponseEntity<LikePostCreateSdo> like(LikePostCreateSdi req) {
        return ResponseEntity.ok(postService.like(req));
    }

    @PostMapping("/unlike")
    public ResponseEntity<LikePostDeleteSdo> like(LikePostDeleteSdi req) {
        return ResponseEntity.ok(postService.unlike(req));
    }

    @PostMapping("/favourites")
    public ResponseEntity<List<PostSelfSdo>> favourites(LikePostJoinUserSdi req) {
        return ResponseEntity.ok(postService.favorites(req));
    }
}
