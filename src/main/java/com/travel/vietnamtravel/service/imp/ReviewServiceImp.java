package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.likereview.sdi.LikeJoinReviewSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewCreateSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewDeleteSdi;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeReviewJoinUserSdi;
import com.travel.vietnamtravel.dto.likereview.sdo.LikeReviewCreateSdo;
import com.travel.vietnamtravel.dto.likereview.sdo.LikeReviewDeleteSdo;
import com.travel.vietnamtravel.dto.review.sdi.*;
import com.travel.vietnamtravel.dto.review.sdo.*;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.media.Image;
import com.travel.vietnamtravel.entity.Review;
import com.travel.vietnamtravel.entity.relationship.LikeReview;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikeReviewRepo;
import com.travel.vietnamtravel.repository.ReviewRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.ReviewService;
import com.travel.vietnamtravel.service.UserInfoService;
import com.travel.vietnamtravel.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.*;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final LikeReviewRepo likeReviewRepo;
    private final ImageService imageService;
    private final UserInfoService userInfoService;
    private final CommonService commonService;

    public ReviewCreateSdo create(ReviewCreateSdi req) {

        Long loginId= commonService.getIdLogin();
        req.setCreateBy(loginId);
        Review review = copyProperties(req, Review.class);

        List<Long> imagesID = imageService.uploadFiles(req.getImage());
        List<Image> images = new ArrayList<>();
        for (Long id : imagesID) {
            Image image = imageService.getImage(id);
            images.add(image);
        }
        review.setImages(images);

        reviewRepo.save(review);
        return ReviewCreateSdo.of(review.getId());
    }

    @Transactional
    public ReviewUpdateSdo update(ReviewUpdateSdi req) {
        Long loginId= commonService.getIdLogin();
        Review review = getReview(req.getId());
        if(!loginId.equals(review.getUserId())){
            throw new CustomException(ERROR_NOT_ROLE);
        }

        List<Image> images = review.getImages();

        if (!req.getDeleteImagesId().isEmpty()) {

            List<Long> deleteImagesId = req.getDeleteImagesId();
            List<Image> deleteImages = new ArrayList<>();
            for (Long id : deleteImagesId) {
                Image image = imageService.getImage(id);
                deleteImages.add(image);
            }

            for (Image image : deleteImages) {
                if (!images.contains(image)) {
                    throw new CustomException(ERROR_NOT_EXIT);
                }
                images.remove(image);
            }
        }

        if (req.getNewImages() != null) {
            List<Long> newImagesId = imageService.uploadFiles(req.getNewImages());
            for (Long id : newImagesId) {
                Image image = imageService.getImage(id);
                images.add(image);
            }
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
        res.setCreateBy(userInfoService.shortSelf(UserInfoSelfSdi.of(review.getUserId())));
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
        req.setLikedBy(commonService.getIdLogin());
        if (likeReviewRepo.existsByUserIDAndReviewId(req.getLikedBy(), req.getReviewId())) {
//            unlike(LikedReviewDeleteSdi.of(req.getLikedBy(), req.getReviewId()));
            throw new CustomException("Error: exit.");
        }
        LikeReview likeReview = DataUtil.copyProperties(req, LikeReview.class);
        likeReviewRepo.save(likeReview);
        return LikeReviewCreateSdo.of(likeReview.getId());
    }

    public LikeReviewDeleteSdo unlike(LikeReviewDeleteSdi req) {

        Long loginId = commonService.getIdLogin();
        LikeReview likeReview = getLikeReview(req.getId());
        if (likeReview.getUserID().equals(loginId)) {

            likeReviewRepo.delete(likeReview);
            return LikeReviewDeleteSdo.of(Boolean.TRUE);
        }
        throw new CustomException(ERROR_NOT_EXIT);

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

    public List<ReviewSelfSdo> listSelf(List<Long> req){
        List<ReviewSelfSdo> res = new ArrayList<>();
        req.stream()
                .map(id -> self(ReviewSelfSdi.of(id)))
                .forEach(res::add);

        return res;
    }

}
