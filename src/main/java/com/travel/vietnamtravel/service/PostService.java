package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.post.sdi.*;
import com.travel.vietnamtravel.dto.post.sdo.PostCreateSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostDeleteSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostSelfSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostUpdateSdo;

import java.util.List;
public interface PostService {
    PostCreateSdo create(PostCreateSdi req);

    PostDeleteSdo delete(PostDeleteSdi req);

    PostUpdateSdo update(PostUpdateSdi req);

    PostSelfSdo self(PostSelfSdi req);

    List<PostSelfSdo> allPosts();

    List<PostSelfSdo> createBy(PostJoinUserSdi req);

    List<PostSelfSdo> postsInPlace(PostJoinPlaceSdi req);
}
