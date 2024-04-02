package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.comment.sdi.*;
import com.travel.vietnamtravel.dto.comment.sdo.CommentCreateSdo;
import com.travel.vietnamtravel.dto.comment.sdo.CommentDeleteSdo;
import com.travel.vietnamtravel.dto.comment.sdo.CommentSelfSdo;
import com.travel.vietnamtravel.dto.comment.sdo.CommentUpdateSdo;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.entity.Comment;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.CommentRepo;
import com.travel.vietnamtravel.service.CommentService;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.UserInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepo commentRepo;
    private final ImageService imageService;
    private final UserInfoService userInfoService;

    public CommentCreateSdo create(CommentCreateSdi req) {
        Comment comment = copyProperties(req, Comment.class);
        if (req.getImage() != null) {
            Long imageId = imageService.uploadFile(req.getImage());
            comment.setImageId(imageId);
        }
        commentRepo.save(comment);
        return CommentCreateSdo.of(comment.getId());

    }

    public CommentDeleteSdo delete(CommentDeleteSdi req) {
        Comment comment = getComment(req.getId());
        if (comment.getImageId() != null) {
            imageService.delete(comment.getImageId());
        }
        commentRepo.delete(comment);
        return CommentDeleteSdo.of(Boolean.TRUE);
    }

    public CommentUpdateSdo update(CommentUpdateSdi req) {
        Comment comment = getComment(req.getId());
        if (req.getImage() == null && comment.getImageId() != null) {
            imageService.delete(comment.getImageId());
            comment.setImageId(null);
        } else if (req.getImage() != null) {
            Long imageId = imageService.uploadFile(req.getImage());
            comment.setImageId(imageId);
        }
        return CommentUpdateSdo.of(Boolean.TRUE);

    }

    public CommentSelfSdo self(CommentSelfSdi req) {
        Comment comment = getComment(req.getId());
        CommentSelfSdo res = copyProperties(comment, CommentSelfSdo.class);
        res.setCreatedBy(userInfoService.shortSelf(UserInfoSelfSdi.of(req.getId())));
        return res;
    }

    public List<CommentSelfSdo> createBy(CommentJoinUserSdi req) {
        List<Long> commentsId = commentRepo.findByUserId(req.getUserId());
        List<CommentSelfSdo> res = new ArrayList<>();
        commentsId.forEach(id -> {
            CommentSelfSdo commentSelf = self(CommentSelfSdi.of(id));
            res.add(commentSelf);
        });
        return res;
    }

    public List<CommentSelfSdo> commentInPost(CommentJoinPostSdi req) {
        List<Long> commentsId = commentRepo.findByPostID(req.getPostId());
        List<CommentSelfSdo> res = new ArrayList<>();
        commentsId.forEach(id -> {
            CommentSelfSdo commentSelf = self(CommentSelfSdi.of(id));
            res.add(commentSelf);
        });
        return res;
    }

    public Comment getComment(Long id) {
        return commentRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }


}
