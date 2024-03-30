package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.likeplace.sdi.LikeJoinPlaceSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceCreateSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceDeleteSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceJoinUserSdi;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceCreateSdo;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceDeleteSdo;
import com.travel.vietnamtravel.dto.likereview.sdi.LikeJoinReviewSdi;
import com.travel.vietnamtravel.dto.place.sdo.PlaceSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.relationship.LikePlace;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikePlaceRepo;
import com.travel.vietnamtravel.service.ReviewService;
import com.travel.vietnamtravel.service.UserInfoService;
import com.travel.vietnamtravel.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
@Service
@RequiredArgsConstructor
public class LikedPlaceServiceImp {
    private final LikePlaceRepo likePlaceRepo;
    private final UserInfoService userInfoService;
    private final ReviewService reviewService;

    public LikePlaceCreateSdo like(LikePlaceCreateSdi req) {
        if (likePlaceRepo.existsByUserIDAndPlaceId(req.getLikedBy(), req.getPlaceID())) {
//            unlike(LikedReviewDeleteSdi.of(req.getLikedBy(), req.getReviewId()));
            throw new CustomException("Error: exit.");
        }
        LikePlace likePlace = DataUtil.copyProperties(req, LikePlace.class);
        likePlaceRepo.save(likePlace);
        return LikePlaceCreateSdo.of(likePlace.getId());
    }

    public LikePlaceDeleteSdo unlike(LikePlaceDeleteSdi req) {

        if (likePlaceRepo.existsByUserIDAndPlaceId(req.getLikedId(), req.getPlaceId())) {

            LikePlace likePlace = likePlaceRepo.findByUserIDAndPlaceId(req.getLikedId(), req.getPlaceId());
            likePlaceRepo.delete(likePlace);

            return LikePlaceDeleteSdo.of(Boolean.TRUE);
        }
        throw new CustomException(ERROR_NOT_EXIT);

    }

    public int totalLikes(LikeJoinReviewSdi req) {
        return likePlaceRepo.countAllByPlaceId(req.getReviewID());
    }

    public List<UserInfoShortSelfSdo> likedBy(LikeJoinPlaceSdi req) {

        List<LikePlace> likePlaces = likePlaceRepo.findByPlaceId(req.getPlaceId());

        List<UserInfoShortSelfSdo> res = new ArrayList<>();

        likePlaces.forEach(lp -> {
            UserInfoShortSelfSdo userInfoShort = userInfoService.shortSelf(UserInfoSelfSdi.of(lp.getUserID()));
            res.add(userInfoShort);
        });

        return res;
    }

    public List<PlaceSelfSdo> favorites(LikePlaceJoinUserSdi req) {
        List<LikePlace> likePlaces = likePlaceRepo.findByUserID(req.getUserId());
        List<PlaceSelfSdo> res = new ArrayList<>();

//        likePlaces.forEach(lr -> {
//            ReviewSelfSdo reviewSelfSdo = reviewService.self(ReviewSelfSdi.of(lr.getReviewId()));
//            res.add(reviewSelfSdo);
//        });
        return res;
    }
}
