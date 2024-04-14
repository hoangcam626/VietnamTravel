package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.likereview.sdi.LikeJoinReviewSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewCreateSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewDeleteSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewJoinUserSdi;
import com.travel.vietnamtravel.dto.likereview.sdo.LikeReviewCreateSdo;
import com.travel.vietnamtravel.dto.likereview.sdo.LikeReviewDeleteSdo;
import com.travel.vietnamtravel.dto.review.sdi.*;
import com.travel.vietnamtravel.dto.review.sdo.ReviewCreateSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewDeleteSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewSelfSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewUpdateSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    ReviewCreateSdo create(ReviewCreateSdi req);
    ReviewUpdateSdo update(ReviewUpdateSdi req);
    ReviewDeleteSdo delete(ReviewDeleteSdi req);
    ReviewSelfSdo self(ReviewSelfSdi req);
    List<ReviewSelfSdo> getReviewsCreateBy(ReviewJoinUserSdi req);
    List<ReviewSelfSdo> getReviewsForPlace(ReviewJoinPlaceSdi req);
    LikeReviewCreateSdo like(LikeReviewCreateSdi req);
    LikeReviewDeleteSdo unlike(LikeReviewDeleteSdi req);
    List<UserInfoShortSelfSdo> likedBy(LikeJoinReviewSdi req);
    List<ReviewSelfSdo> favorites(LikeReviewJoinUserSdi req);
}
