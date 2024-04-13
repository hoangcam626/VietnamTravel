package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.review.sdi.*;
import com.travel.vietnamtravel.dto.review.sdo.*;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.entity.media.Image;
import com.travel.vietnamtravel.entity.Review;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikeReviewRepo;
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

    public List<ReviewSelfSdo> getReviewsCreateBy(ReviewJoinUserSdi req) {

        List<Review> reviews = reviewRepo.findAllByUserId(req.getCreateBy());

        List<ReviewSelfSdo> res = new ArrayList<>();
        reviews.forEach(review -> {
            res.add(self(ReviewSelfSdi.of(review.getId())));
        });

        return res;
    }

    public List<ReviewSelfSdo> getReviewsForPlace(ReviewJoinPlaceSdi req) {

        List<Review> reviews = reviewRepo.findAllByPlaceId(req.getPlaceId());

        List<ReviewSelfSdo> res = new ArrayList<>();
        reviews.forEach(review -> {
            res.add(self(ReviewSelfSdi.of(review.getId())));
        });

        return res;
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

    public Review getReview(Long id) {
        return reviewRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

}
