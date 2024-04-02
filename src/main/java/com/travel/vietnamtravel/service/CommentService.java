package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.comment.sdi.*;
import com.travel.vietnamtravel.dto.comment.sdo.*;

import java.util.List;

public interface CommentService {
    CommentCreateSdo create(CommentCreateSdi req);

    CommentDeleteSdo delete(CommentDeleteSdi req);

    CommentUpdateSdo update(CommentUpdateSdi req);

    CommentSelfSdo self(CommentSelfSdi req);

    List<CommentSelfSdo> createBy(CommentJoinUserSdi req);

    List<CommentSelfSdo> commentInPost(CommentJoinPostSdi req);

    List<CommentSelfSdo> subComments(CommentSelfSdi req);
}
