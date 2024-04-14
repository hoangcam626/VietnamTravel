package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.likeplace.sdi.LikeJoinPlaceSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceCreateSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceDeleteSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceJoinUserSdi;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceCreateSdo;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceDeleteSdo;
import com.travel.vietnamtravel.dto.place.sdi.*;
import com.travel.vietnamtravel.dto.place.sdo.*;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.Place;

import java.util.List;

public interface PlaceService {

    PlaceCreateSdo create(PlaceCreateSdi req);

    PlaceDeleteSdo delete(PlaceDeleteSdi req);

    PlaceUpdateSdo update(PlaceUpdateSdi req);

    PlaceSelfSdo self(PlaceSelfSdi req);

    Place getPlace(Long id);

    PlaceRatingSdo showRating(PlaceRatingSdi req);

    LikePlaceCreateSdo like(LikePlaceCreateSdi req);

    LikePlaceDeleteSdo unlike(LikePlaceDeleteSdi req);

    List<UserInfoShortSelfSdo> likedBy(LikeJoinPlaceSdi req);

    List<PlaceSelfSdo> favorites(LikePlaceJoinUserSdi req);
}
