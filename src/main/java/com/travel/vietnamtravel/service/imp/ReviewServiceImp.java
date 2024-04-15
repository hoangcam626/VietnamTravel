package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.likereview.sdi.*;
import com.travel.vietnamtravel.dto.likereview.sdo.*;
import com.travel.vietnamtravel.dto.review.sdi.*;
import com.travel.vietnamtravel.dto.review.sdo.*;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.Review;
import com.travel.vietnamtravel.entity.relationship.LikeReview;
import com.travel.vietnamtravel.entity.relationship.ReviewImage;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikeReviewRepo;
import com.travel.vietnamtravel.repository.ReviewImageRepo;
import com.travel.vietnamtravel.repository.ReviewRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.ReviewService;
import com.travel.vietnamtravel.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.*;
import static com.travel.vietnamtravel.util.DataUtil.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final ReviewImageRepo reviewImageRepo;
    private final LikeReviewRepo likeReviewRepo;
    private final ImageService imageService;
    private final UserInfoService userInfoService;
    private final CommonService commonService;

    public ReviewCreateSdo create(ReviewCreateSdi req) {

        Long loginId = commonService.getIdLogin();
        req.setCreateBy(loginId);
        Review review = copyProperties(req, Review.class);

        List<Long> imageIds = imageService.uploadFiles(req.getImage());
        reviewRepo.save(review);
        imageIds.stream()
                .map(imageId -> ReviewImage.builder()
                        .imageId(imageId)
                        .reviewId(review.getId()).build())
                .forEach(reviewImageRepo::save);
        return ReviewCreateSdo.of(review.getId());
    }

    @Transactional
    public ReviewUpdateSdo update(ReviewUpdateSdi req) {

        Long loginId = commonService.getIdLogin();
        Review review = getReview(req.getId());
        if (!loginId.equals(review.getCreatedBy())) {
            throw new CustomException(ERROR_NOT_ROLE);
        }

//        List<Image> images = review.getImages();
//
//        if (!req.getDeleteImagesId().isEmpty()) {
//
//            List<Long> deleteImagesId = req.getDeleteImagesId();
//            List<Image> deleteImages = new ArrayList<>();
//            for (Long id : deleteImagesId) {
//                Image image = imageService.getImage(id);
//                deleteImages.add(image);
//            }
//
//            for (Image image : deleteImages) {
//                if (!images.contains(image)) {
//                    throw new CustomException(ERROR_NOT_EXIT);
//                }
//                images.remove(image);
//            }
//        }
        List<ReviewImage> reviewImages = getReviewImage(review.getId());
        if (!isNullOrEmpty(reviewImages)) {
            reviewImages.stream().map(ReviewImage::getImageId)
                    .forEach(imageService::delete);
            reviewImageRepo.deleteAll(reviewImages);
        }

        if (!isNullObject(req.getNewImages())) {
            List<Long> newImagesId = imageService.uploadFiles(req.getNewImages());
            newImagesId.stream()
                    .map(imageId -> ReviewImage.builder()
                            .imageId(imageId)
                            .reviewId(review.getId()).build())
                    .forEach(reviewImageRepo::save);
        }

        review.setRating(req.getRating());
        review.setDescription(req.getDescription());
        reviewRepo.save(review);

        return ReviewUpdateSdo.of(Boolean.TRUE);
    }

    public ReviewDeleteSdo delete(ReviewDeleteSdi req) {
        Review review = getReview(req.getId());
        reviewRepo.delete(review);
        return ReviewDeleteSdo.of(Boolean.TRUE);
    }

    public ReviewSelfSdo self(ReviewSelfSdi req) {

        Review review = getReview(req.getId());

        ReviewSelfSdo res = copyProperties(review, ReviewSelfSdo.class);
        res.setImagesId(getImageIds(review.getId()));
        res.setCreateBy(userInfoService.shortSelf(UserInfoSelfSdi.of(review.getCreatedBy())));
        res.setTotalLike(likeReviewRepo.countAllByReviewId(review.getId()));
        Long loginId = commonService.getIdLogin();
        res.setIsLike(likeReviewRepo.existsByUserIDAndReviewId(loginId, review.getId()));
        return res;
    }

    public List<ReviewSelfSdo> getReviewsCreateBy(ReviewJoinUserSdi req) {

        List<Long> reviewIds = reviewRepo.findAllByUserId(req.getCreateBy());
        return listSelf(reviewIds);
    }

    public List<ReviewSelfSdo> getReviewsForPlace(ReviewJoinPlaceSdi req) {

        List<Long> reviewIds = reviewRepo.findAllByPlaceId(req.getPlaceId());
        return listSelf(reviewIds);
    }

    public LikeReviewCreateSdo like(LikeReviewCreateSdi req) {
        Long loginId = commonService.getIdLogin();
        if (likeReviewRepo.existsByUserIDAndReviewId(loginId, req.getReviewId())) {
            throw new CustomException(ERROR_ALREADY_EXIT);
        }
        LikeReview likeReview = LikeReview.builder()
                .userID(loginId)
                .reviewId(req.getReviewId())
                .build();
        likeReviewRepo.save(likeReview);
        return LikeReviewCreateSdo.of(likeReview.getId());
    }

    public LikeReviewDeleteSdo unlike(LikeReviewDeleteSdi req) {

        Long loginId = commonService.getIdLogin();
        if (!likeReviewRepo.existsByUserIDAndReviewId(loginId, req.getReviewId())) {
            throw new CustomException(ERROR_NOT_EXIT);
        }
        LikeReview delete = likeReviewRepo.findByUserIDAndReviewId(loginId, req.getReviewId());
        likeReviewRepo.delete(delete);
        return LikeReviewDeleteSdo.of(Boolean.TRUE);
    }

    public List<UserInfoShortSelfSdo> likedBy(LikeJoinReviewSdi req) {

        List<LikeReview> likeReviews = likeReviewRepo.findByReviewId(req.getReviewID());
        List<UserInfoShortSelfSdo> res = new ArrayList<>();
        likeReviews.forEach(lr -> {
            UserInfoShortSelfSdo userInfoShort = userInfoService.shortSelf(UserInfoSelfSdi.of(lr.getUserID()));
            res.add(userInfoShort);
        });

        return res;
    }

    public List<ReviewSelfSdo> favorites(LikeReviewJoinUserSdi req) {

        List<LikeReview> likeReviews = likeReviewRepo.findByUserId(req.getUserId());
        List<ReviewSelfSdo> res = new ArrayList<>();
        likeReviews.forEach(lr -> {
            ReviewSelfSdo reviewSelfSdo = self(ReviewSelfSdi.of(lr.getReviewId()));
            res.add(reviewSelfSdo);
        });
        return res;
    }

    public LikeReview getLikeReview(Long id) {
        return likeReviewRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

    public Review getReview(Long id) {
        return reviewRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

    public List<ReviewImage> getReviewImage(Long id) {
        return reviewImageRepo.findByReviewId(id);
    }

    public List<Long> getImageIds(Long id) {
        return reviewImageRepo.findImageByReviewId(id);
    }

    public List<ReviewSelfSdo> listSelf(List<Long> req) {
        List<ReviewSelfSdo> res = new ArrayList<>();
        req.stream()
                .map(id -> self(ReviewSelfSdi.of(id)))
                .forEach(res::add);

        return res;
    }

}
