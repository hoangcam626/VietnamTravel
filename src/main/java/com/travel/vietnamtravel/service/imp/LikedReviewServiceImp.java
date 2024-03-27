package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewCreateSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewDeleteSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeJoinReviewSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewJoinUserSdi;
import com.travel.vietnamtravel.dto.likereview.sdo.LikeReviewCreateSdo;
import com.travel.vietnamtravel.dto.likereview.sdo.LikeReviewDeleteSdo;
import com.travel.vietnamtravel.dto.review.sdi.ReviewSelfSdi;
import com.travel.vietnamtravel.dto.review.sdo.ReviewSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.LikeReview;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikeReviewRepo;
import com.travel.vietnamtravel.service.LikedReviewService;
import com.travel.vietnamtravel.service.ReviewService;
import com.travel.vietnamtravel.service.UserInfoService;
import com.travel.vietnamtravel.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.*;

@Service
@RequiredArgsConstructor
public class LikedReviewServiceImp implements LikedReviewService {
    private final LikeReviewRepo likedReviewRepository;
    private final UserInfoService userInfoService;
    private final ReviewService reviewService;

    public LikeReviewCreateSdo like(LikeReviewCreateSdi req) {
        if (likedReviewRepository.existsByUserIDAndReviewId(req.getLikedBy(), req.getReviewId())) {
//            unlike(LikedReviewDeleteSdi.of(req.getLikedBy(), req.getReviewId()));
            throw new CustomException("Error: exit.");
        }
        LikeReview likeReview = DataUtil.copyProperties(req, LikeReview.class);
        likedReviewRepository.save(likeReview);
        return LikeReviewCreateSdo.of(likeReview.getId());
    }

    public LikeReviewDeleteSdo unlike(LikeReviewDeleteSdi req) {

        if (likedReviewRepository.existsByUserIDAndReviewId(req.getLikedBy(), req.getReviewId())) {

            LikeReview likeReview = likedReviewRepository.findByUserIDAndReviewId(req.getLikedBy(), req.getReviewId());
            likedReviewRepository.delete(likeReview);

            return LikeReviewDeleteSdo.of(Boolean.TRUE);
        }
        throw new CustomException(ERROR_NOT_EXIT);

    }

    public int totalLikes(LikeJoinReviewSdi req) {
        return likedReviewRepository.countAllByReviewId(req.getReviewID());
    }

    public List<UserInfoShortSelfSdo> likedBy(LikeJoinReviewSdi req) {

        List<LikeReview> likeReviews = likedReviewRepository.findByReviewId(req.getReviewID());

        List<UserInfoShortSelfSdo> res = new ArrayList<>();

        likeReviews.forEach(lr -> {
            UserInfoShortSelfSdo userInfoShort = userInfoService.shortSelf(UserInfoSelfSdi.of(lr.getUserID()));
            res.add(userInfoShort);
        });

        return res;
    }

    public List<ReviewSelfSdo> favorites(LikeReviewJoinUserSdi req) {
        List<LikeReview> likeReviews = likedReviewRepository.findByUserId(req.getUserId());
        List<ReviewSelfSdo> res = new ArrayList<>();

        likeReviews.forEach(lr -> {
            ReviewSelfSdo reviewSelfSdo = reviewService.self(ReviewSelfSdi.of(lr.getReviewId()));
            res.add(reviewSelfSdo);
        });
        return res;
    }
}
