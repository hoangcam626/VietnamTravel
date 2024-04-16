package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.administrative.districts.sdi.DistrictSelfSdi;
import com.travel.vietnamtravel.dto.administrative.provinces.sdi.ProvinceSelfSdi;
import com.travel.vietnamtravel.dto.administrative.wards.sdi.WardSelfSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.*;
import com.travel.vietnamtravel.dto.likeplace.sdo.*;
import com.travel.vietnamtravel.dto.place.sdi.*;
import com.travel.vietnamtravel.dto.place.sdo.*;

import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.Place;
import com.travel.vietnamtravel.entity.relationship.LikePlace;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikePlaceRepo;
import com.travel.vietnamtravel.repository.PlaceRepo;
import com.travel.vietnamtravel.repository.ReviewRepo;
import com.travel.vietnamtravel.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_ALREADY_EXIT;
import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceServiceImp implements PlaceService {
    private final PlaceRepo placeRepo;
    private final LikePlaceRepo likePlaceRepo;
    private final ReviewRepo reviewRepo;
    private final UserInfoService userInfoService;
    private final CommonService commonService;
    private final ImageService imageService;
    private final AdministrativeService administrativeService;

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

        res.setProvince(administrativeService.shortSelf(ProvinceSelfSdi.of(place.getProvinceCode())));
        res.setDistrict(administrativeService.shortSelf(DistrictSelfSdi.of(place.getDistrictCode())));
        res.setWard(administrativeService.shortSelf(WardSelfSdi.of(place.getWardCode())));

        res.setTotalReview(reviewRepo.countReviewByPlaceId(place.getId()));
        res.setRating(reviewRepo.rating(place.getId()));

        Long userId = commonService.getIdLogin();
        res.setIsLike(likePlaceRepo.existsByUserIdAndPlaceId(userId, place.getId()));
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

    public LikePlaceCreateSdo like(LikePlaceCreateSdi req) {
        Long loginId = commonService.getIdLogin();
        if (likePlaceRepo.existsByUserIdAndPlaceId(loginId, req.getPlaceId())) {
            throw new CustomException(ERROR_ALREADY_EXIT);
        }
        LikePlace likePlace = LikePlace.builder()
                .userId(loginId)
                .placeId(req.getPlaceId())
                .build();
        likePlaceRepo.save(likePlace);
        return LikePlaceCreateSdo.of(likePlace.getId());
    }

    public LikePlaceDeleteSdo unlike(LikePlaceDeleteSdi req) {

        Long loginId = commonService.getIdLogin();
        if (!likePlaceRepo.existsByUserIdAndPlaceId(loginId, req.getPlaceId())) {
            throw new CustomException(ERROR_NOT_EXIT);
        }
        LikePlace delete = likePlaceRepo.findByUserIdAndPlaceId(loginId, req.getPlaceId());
        likePlaceRepo.delete(delete);
        return LikePlaceDeleteSdo.of(Boolean.TRUE);
    }

    public List<UserInfoShortSelfSdo> likedBy(LikeJoinPlaceSdi req) {

        List<LikePlace> likePlaces = likePlaceRepo.findByPlaceId(req.getPlaceId());
        List<UserInfoShortSelfSdo> res = new ArrayList<>();

        likePlaces.stream()
                .map(lp -> userInfoService.shortSelf(UserInfoSelfSdi.of(lp.getUserId())))
                .forEach(res::add);
        return res;
    }

    public List<PlaceSelfSdo> favorites(LikePlaceJoinUserSdi req) {
        List<LikePlace> likePlaces = likePlaceRepo.findByUserID(req.getUserId());
        List<PlaceSelfSdo> res = new ArrayList<>();

        likePlaces.stream()
                .map(lp -> self(PlaceSelfSdi.of(lp.getPlaceId())))
                .forEach(res::add);

        return res;
    }

    public Place getPlace(Long id) {
        return placeRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

    public List<PlaceSelfSdo> listSelf(List<Long> req) {
        List<PlaceSelfSdo> res = new ArrayList<>();
        req.stream()
                .map(id -> self(PlaceSelfSdi.of(id)))
                .forEach(res::add);
        return res;
    }
}
