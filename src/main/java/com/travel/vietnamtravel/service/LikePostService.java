package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.post.sdo.PostSelfSdo;
import com.travel.vietnamtravel.dto.likepost.sdi.LikePostCreateSdi;
import com.travel.vietnamtravel.dto.likepost.sdi.LikePostDeleteSdi;
import com.travel.vietnamtravel.dto.likepost.sdi.LikePostJoinUserSdi;
import com.travel.vietnamtravel.dto.likepost.sdi.LikeJoinPostSdi;
import com.travel.vietnamtravel.dto.likepost.sdo.LikePostCreateSdo;
import com.travel.vietnamtravel.dto.likepost.sdo.LikePostDeleteSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;

import java.util.List;

public interface LikePostService {
    LikePostCreateSdo like(LikePostCreateSdi req);

    LikePostDeleteSdo unlike(LikePostDeleteSdi req);

//    Long totalLikes(LikeJoinPostSdi req);

    List<UserInfoShortSelfSdo> likedBy(LikeJoinPostSdi req);

    List<PostSelfSdo> favorites(LikePostJoinUserSdi req);
}
