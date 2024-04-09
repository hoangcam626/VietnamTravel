package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.review.sdi.*;
import com.travel.vietnamtravel.dto.review.sdo.*;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.entity.media.Image;
import com.travel.vietnamtravel.entity.Review;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikeReviewRepo;
import com.travel.vietnamtravel.repository.ReviewRepo;
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

    private final ImageService imageService;
    private final UserInfoService userInfoService;
    private final LikeReviewRepo likedReviewRepository;
    private final ReviewRepo reviewRepository;
    public ReviewCreateSdo create(ReviewCreateSdi req) {

        Review review = copyProperties(req, Review.class);

        List<Long> imagesID = imageService.uploadFiles(req.getImage());
        List<Image> images = new ArrayList<>();
        for (Long id : imagesID) {
            Image image = imageService.getImage(id);
            images.add(image);
        }
        review.setImages(images);

        reviewRepository.save(review);
        return ReviewCreateSdo.of(review.getId());
    }

    @Transactional
    public ReviewUpdateSdo update(ReviewUpdateSdi req) {
        Review review = getReview(req.getId());

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
        reviewRepository.save(review);

        return ReviewUpdateSdo.of(Boolean.TRUE);
    }

    public ReviewDeleteSdo delete(ReviewDeleteSdi req) {
        Review review = getReview(req.getId());
        reviewRepository.delete(review);
        return ReviewDeleteSdo.of(Boolean.TRUE);
    }

    public List<ReviewSelfSdo> getReviewsCreateBy(ReviewJoinUserSdi req) {

        List<Review> reviews = reviewRepository.findAllByUserId(req.getCreateBy());

        List<ReviewSelfSdo> res = new ArrayList<>();
        reviews.forEach(review -> {
            res.add(self(ReviewSelfSdi.of(review.getId())));
        });

        return res;
    }
    public List<ReviewSelfSdo> getReviewsForPlace(ReviewJoinPlaceSdi req) {

        List<Review> reviews = reviewRepository.findAllByPlaceId(req.getPlaceId());

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
        res.setTotalLike(likedReviewRepository.countAllByReviewId(review.getId()));
        return res;
    }

    public Review getReview(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

}
