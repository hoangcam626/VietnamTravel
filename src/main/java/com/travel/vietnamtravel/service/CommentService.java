package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.comment.sdi.*;
import com.travel.vietnamtravel.dto.comment.sdo.CommentCreateSdo;
import com.travel.vietnamtravel.dto.comment.sdo.CommentDeleteSdo;
import com.travel.vietnamtravel.dto.comment.sdo.CommentSelfSdo;
import com.travel.vietnamtravel.dto.comment.sdo.CommentUpdateSdo;

import java.util.List;

public interface CommentService {
    CommentCreateSdo create(CommentCreateSdi req);

    CommentDeleteSdo delete(CommentDeleteSdi req);

    CommentUpdateSdo update(CommentUpdateSdi req);

    CommentSelfSdo self(CommentSelfSdi req);

    List<CommentSelfSdo> createBy(CommentJoinUserSdi req);

    List<CommentSelfSdo> commentInPost(CommentJoinPostSdi req);
}
