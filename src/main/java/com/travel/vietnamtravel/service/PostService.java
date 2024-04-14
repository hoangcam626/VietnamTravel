package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.likepost.sdi.LikeJoinPostSdi;
import com.travel.vietnamtravel.dto.likepost.sdi.LikePostCreateSdi;
import com.travel.vietnamtravel.dto.likepost.sdi.LikePostDeleteSdi;
import com.travel.vietnamtravel.dto.likepost.sdi.LikePostJoinUserSdi;
import com.travel.vietnamtravel.dto.likepost.sdo.LikePostCreateSdo;
import com.travel.vietnamtravel.dto.likepost.sdo.LikePostDeleteSdo;
import com.travel.vietnamtravel.dto.post.sdi.*;
import com.travel.vietnamtravel.dto.post.sdo.PostCreateSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostDeleteSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostSelfSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostUpdateSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;

import java.util.List;
public interface PostService {
    PostCreateSdo create(PostCreateSdi req);

    PostDeleteSdo delete(PostDeleteSdi req);

    PostUpdateSdo update(PostUpdateSdi req);

    PostSelfSdo self(PostSelfSdi req);

    List<PostSelfSdo> allPosts();

    List<PostSelfSdo> createBy(PostJoinUserSdi req);

    List<PostSelfSdo> postsInPlace(PostJoinPlaceSdi req);

    LikePostCreateSdo like(LikePostCreateSdi req);

    LikePostDeleteSdo unlike(LikePostDeleteSdi req);

    List<UserInfoShortSelfSdo> likedBy(LikeJoinPostSdi req);

    List<PostSelfSdo> favorites(LikePostJoinUserSdi req);

}
