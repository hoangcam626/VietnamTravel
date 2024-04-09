package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewCreateSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewDeleteSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeJoinReviewSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewJoinUserSdi;
import com.travel.vietnamtravel.dto.likereview.sdo.LikeReviewCreateSdo;
import com.travel.vietnamtravel.dto.likereview.sdo.LikeReviewDeleteSdo;
import com.travel.vietnamtravel.dto.review.sdo.ReviewSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;

import java.util.List;

public interface LikeReviewService {
    LikeReviewCreateSdo like(LikeReviewCreateSdi req);
    LikeReviewDeleteSdo unlike(LikeReviewDeleteSdi req);
    List<UserInfoShortSelfSdo> likedBy(LikeJoinReviewSdi req);
    List<ReviewSelfSdo> favorites(LikeReviewJoinUserSdi req);
//    Long totalLikes(LikeJoinReviewSdi req);
}
