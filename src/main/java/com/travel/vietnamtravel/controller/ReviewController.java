package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.review.sdi.*;
import com.travel.vietnamtravel.dto.review.sdo.ReviewCreateSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewDeleteSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewSelfSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewUpdateSdo;
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

    @PostMapping
    public ResponseEntity<ReviewCreateSdo> create(@RequestBody ReviewCreateSdi req){
        return ResponseEntity.ok(reviewService.create(req));
    }
    @PutMapping
    public ResponseEntity<ReviewUpdateSdo> update(@RequestBody ReviewUpdateSdi req){
        return ResponseEntity.ok(reviewService.update(req));
    }
    @DeleteMapping
    public ResponseEntity<ReviewDeleteSdo> delete(@RequestBody ReviewDeleteSdi req){
        return ResponseEntity.ok(reviewService.delete(req));
    }

    @GetMapping
    public ResponseEntity<ReviewSelfSdo> self(@RequestBody ReviewSelfSdi req){
        return ResponseEntity.ok(reviewService.self(req));
    }

    @GetMapping("/reviews/creat-by")
    public ResponseEntity<List<ReviewSelfSdo>> getReviewsCreateBy(@RequestBody ReviewJoinUserSdi req){
        return ResponseEntity.ok(reviewService.getReviewsCreateBy(req));
    }
    @GetMapping("/reviews/for-place")
    public ResponseEntity<List<ReviewSelfSdo>> getReviewsForPlace(@RequestBody ReviewJoinPlaceSdi req){
        return ResponseEntity.ok(reviewService.getReviewsForPlace(req));
    }
}