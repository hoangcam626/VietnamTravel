package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.comment.sdo.CommentSelfSdo;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentCreateSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentDeleteSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentJoinUserSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeJoinCommentSdi;
import com.travel.vietnamtravel.dto.likecomment.sdo.LikeCommentCreateSdo;
import com.travel.vietnamtravel.dto.likecomment.sdo.LikeCommentDeleteSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;

import java.util.List;

public interface LikeCommentService {
    LikeCommentCreateSdo like(LikeCommentCreateSdi req);

    LikeCommentDeleteSdo unlike(LikeCommentDeleteSdi req);

//    Long totalLikes(LikeJoinCommentSdi req);

    List<UserInfoShortSelfSdo> likedBy(LikeJoinCommentSdi req);

    List<CommentSelfSdo> favorites(LikeCommentJoinUserSdi req);
}
