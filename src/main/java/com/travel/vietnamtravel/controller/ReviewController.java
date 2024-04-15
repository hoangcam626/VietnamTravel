package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.likereview.sdi.*;
import com.travel.vietnamtravel.dto.likereview.sdo.*;
import com.travel.vietnamtravel.dto.review.sdi.*;
import com.travel.vietnamtravel.dto.review.sdo.*;
import com.travel.vietnamtravel.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/create")
    public ResponseEntity<ReviewCreateSdo> create(@RequestBody ReviewCreateSdi req){
        return ResponseEntity.ok(reviewService.create(req));
    }
    @PutMapping("/update")
    public ResponseEntity<ReviewUpdateSdo> update(@RequestBody ReviewUpdateSdi req){
        return ResponseEntity.ok(reviewService.update(req));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ReviewDeleteSdo> delete(@RequestBody ReviewDeleteSdi req){
        return ResponseEntity.ok(reviewService.delete(req));
    }

    @GetMapping("/self")
    public ResponseEntity<ReviewSelfSdo> self(@RequestBody ReviewSelfSdi req){
        return ResponseEntity.ok(reviewService.self(req));
    }

    @GetMapping("/reviews/create-by")
    public ResponseEntity<List<ReviewSelfSdo>> getReviewsCreateBy(@RequestBody ReviewJoinUserSdi req){
        return ResponseEntity.ok(reviewService.getReviewsCreateBy(req));
    }
    @GetMapping("/reviews/for-place")
    public ResponseEntity<List<ReviewSelfSdo>> getReviewsForPlace(@RequestBody ReviewJoinPlaceSdi req){
        return ResponseEntity.ok(reviewService.getReviewsForPlace(req));
    }

    @PostMapping("/like")
    public ResponseEntity<LikeReviewCreateSdo> like(@RequestBody LikeReviewCreateSdi req){
        return ResponseEntity.ok(reviewService.like(req));
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<LikeReviewDeleteSdo> unlike(@RequestBody LikeReviewDeleteSdi req){
        return ResponseEntity.ok(reviewService.unlike(req));
    }
    @GetMapping("/favourites")
    public ResponseEntity<List<ReviewSelfSdo>> favourites(LikeReviewJoinUserSdi req){
        return ResponseEntity.ok(reviewService.favorites(req));
    }
}
