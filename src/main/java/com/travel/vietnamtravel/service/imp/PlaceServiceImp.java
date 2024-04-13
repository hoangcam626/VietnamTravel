package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.place.sdi.*;
import com.travel.vietnamtravel.dto.place.sdo.*;
import com.travel.vietnamtravel.entity.Place;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikePlaceRepo;
import com.travel.vietnamtravel.repository.PlaceRepo;
import com.travel.vietnamtravel.repository.ReviewRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceServiceImp implements PlaceService {
    private final PlaceRepo placeRepo;
    private final ImageService imageService;
    private final LikePlaceRepo likePlaceRepo;
    private final ReviewRepo reviewRepo;
    private final CommonService commonService;

    public PlaceCreateSdo create(PlaceCreateSdi req) {
        Place place = copyProperties(req, Place.class);
        Long imageId = imageService.uploadFile(req.getImage());
        place.setImageId(imageId);
        placeRepo.save(place);
        return PlaceCreateSdo.of(place.getId());
    }

    public PlaceDeleteSdo delete(PlaceDeleteSdi req) {
        Place place = getPlace(req.getId());
        placeRepo.delete(place);
        return PlaceDeleteSdo.of(Boolean.TRUE);
    }

    public PlaceUpdateSdo update(PlaceUpdateSdi req) {

        Place place = getPlace(req.getId());

        if (!req.getImage().isEmpty()) {
            imageService.delete(place.getImageId());
            Long newImageId = imageService.uploadFile(req.getImage());
            place.setImageId(newImageId);
        }

        place.setName(req.getName());
        place.setDescription(req.getDescription());
        place.setWardCode(req.getWardCode());
        place.setDistrictCode(req.getDistrictCode());
        place.setProvinceCode(req.getProvinceCode());
        placeRepo.save(place);
        return PlaceUpdateSdo.of(Boolean.TRUE);

    }

    public PlaceSelfSdo self(PlaceSelfSdi req) {

        Place place = getPlace(req.getId());

        PlaceSelfSdo res = copyProperties(place, PlaceSelfSdo.class);
        res.setRating(reviewRepo.rating(place.getId()));
        res.setTotalReview(reviewRepo.countReviewByPlaceId(place.getId()));
        Long userId = commonService.getIdLogin();
        res.setIsLike(likePlaceRepo.existsByUserIDAndPlaceId(userId, place.getId()));
        res.setTotalLike(likePlaceRepo.countLikeByPlaceId(place.getId()));

        return res;
    }

    public PlaceRatingSdo showRating(PlaceRatingSdi req) {
        PlaceRatingSdo res = new PlaceRatingSdo();
        res.setOneStar(reviewRepo.countRating(req.getId(), 1));
        res.setTwoStar(reviewRepo.countRating(req.getId(), 2));
        res.setThreeStar(reviewRepo.countRating(req.getId(), 3));
        res.setFourStar(reviewRepo.countRating(req.getId(), 4));
        res.setFiveStar(reviewRepo.countRating(req.getId(), 5));
        return res;
    }

    public Place getPlace(Long id) {
        return placeRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

}
