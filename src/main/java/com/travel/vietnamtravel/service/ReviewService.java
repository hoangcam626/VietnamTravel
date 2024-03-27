package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.review.sdi.*;
import com.travel.vietnamtravel.dto.review.sdo.ReviewCreateSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewDeleteSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewSelfSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewUpdateSdo;
import com.travel.vietnamtravel.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    ReviewCreateSdo create(ReviewCreateSdi req);
    ReviewUpdateSdo update(ReviewUpdateSdi req);
    ReviewDeleteSdo delete(ReviewDeleteSdi req);
    List<ReviewSelfSdo> getReviewsCreateBy(ReviewJoinUserSdi req);
    List<ReviewSelfSdo> getReviewsForPlace(ReviewJoinPlaceSdi req);
    ReviewSelfSdo self(ReviewSelfSdi req);
    Review getReview(Long id);

}
