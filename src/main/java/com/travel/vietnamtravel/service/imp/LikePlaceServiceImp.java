package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.likeplace.sdi.LikeJoinPlaceSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceCreateSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceDeleteSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceJoinUserSdi;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceCreateSdo;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceDeleteSdo;
import com.travel.vietnamtravel.dto.place.sdi.PlaceSelfSdi;
import com.travel.vietnamtravel.dto.place.sdo.PlaceSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.relationship.LikePlace;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikePlaceRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.LikePlaceService;
import com.travel.vietnamtravel.service.PlaceService;
import com.travel.vietnamtravel.service.UserInfoService;
import com.travel.vietnamtravel.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_ALREADY_EXIT;
import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;

@Service
@RequiredArgsConstructor
public class LikePlaceServiceImp implements LikePlaceService {
    private final LikePlaceRepo likePlaceRepo;
    private final UserInfoService userInfoService;
    private final PlaceService placeService;
    private final CommonService commonService;

    public LikePlaceCreateSdo like(LikePlaceCreateSdi req) {
        req.setLikedBy(commonService.getIdLogin());
        if (likePlaceRepo.existsByUserIDAndPlaceId(req.getLikedBy(), req.getPlaceID())) {
//            unlike(LikedReviewDeleteSdi.of(req.getLikedBy(), req.getReviewId()));
            throw new CustomException(ERROR_ALREADY_EXIT);
        }
        LikePlace likePlace = DataUtil.copyProperties(req, LikePlace.class);
        likePlaceRepo.save(likePlace);
        return LikePlaceCreateSdo.of(likePlace.getId());
    }

    public LikePlaceDeleteSdo unlike(LikePlaceDeleteSdi req) {

        Long loginId = commonService.getIdLogin();
        LikePlace likePlace = getLikePlace(req.getId());
        if (likePlace.getUserID().equals(loginId)) {
            likePlaceRepo.delete(likePlace);
            return LikePlaceDeleteSdo.of(Boolean.TRUE);
        }
        throw new CustomException(ERROR_NOT_EXIT);

    }
    public List<UserInfoShortSelfSdo> likedBy(LikeJoinPlaceSdi req) {

        List<LikePlace> likePlaces = likePlaceRepo.findByPlaceId(req.getPlaceId());
        List<UserInfoShortSelfSdo> res = new ArrayList<>();

        likePlaces.stream()
                .map(lp -> userInfoService.shortSelf(UserInfoSelfSdi.of(lp.getUserID())))
                .forEach(res::add);
        return res;
    }

    public List<PlaceSelfSdo> favorites(LikePlaceJoinUserSdi req) {
        List<LikePlace> likePlaces = likePlaceRepo.findByUserID(req.getUserId());
        List<PlaceSelfSdo> res = new ArrayList<>();

        likePlaces.stream()
                .map(lp -> placeService.self(PlaceSelfSdi.of(lp.getPlaceId())))
                .forEach(res::add);

        return res;
    }
//    public Long totalLikes(LikeJoinPlaceSdi req) {
//        return likePlaceRepo.countLikeByPlaceId(req.getPlaceId());
//    }
    public LikePlace getLikePlace(Long id) {
        return likePlaceRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }
}

