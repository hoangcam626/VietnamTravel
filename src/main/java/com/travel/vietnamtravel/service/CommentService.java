package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.comment.sdi.*;
import com.travel.vietnamtravel.dto.comment.sdo.*;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentCreateSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentDeleteSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentJoinUserSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeJoinCommentSdi;
import com.travel.vietnamtravel.dto.likecomment.sdo.LikeCommentCreateSdo;
import com.travel.vietnamtravel.dto.likecomment.sdo.LikeCommentDeleteSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;

import java.util.List;

public interface CommentService {
    CommentCreateSdo create(CommentCreateSdi req);

    CommentDeleteSdo delete(CommentDeleteSdi req);

    CommentUpdateSdo update(CommentUpdateSdi req);

    CommentSelfSdo self(CommentSelfSdi req);

    List<CommentSelfSdo> createBy(CommentJoinUserSdi req);

    List<CommentSelfSdo> commentsInPost(CommentJoinPostSdi req);

    List<CommentSelfSdo> subComments(CommentSelfSdi req);

    LikeCommentCreateSdo like(LikeCommentCreateSdi req);

    LikeCommentDeleteSdo unlike(LikeCommentDeleteSdi req);

    List<UserInfoShortSelfSdo> likedBy(LikeJoinCommentSdi req);

    List<CommentSelfSdo> favorites(LikeCommentJoinUserSdi req);
}
