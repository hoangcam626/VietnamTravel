package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.likeplace.sdi.LikeJoinPlaceSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceCreateSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceDeleteSdi;
import com.travel.vietnamtravel.dto.likeplace.sdi.LikePlaceJoinUserSdi;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceCreateSdo;
import com.travel.vietnamtravel.dto.likeplace.sdo.LikePlaceDeleteSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import java.util.List;

public interface LikePlaceService {

    LikePlaceCreateSdo like(LikePlaceCreateSdi req);

    LikePlaceDeleteSdo unlike(LikePlaceDeleteSdi req);

    Long totalLikes(LikeJoinPlaceSdi req);

    List<UserInfoShortSelfSdo> likedBy(LikeJoinPlaceSdi req);

    List<PlaceSelfSdo> favorites(LikePlaceJoinUserSdi req);
}
