package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.comment.sdi.*;
import com.travel.vietnamtravel.dto.comment.sdo.*;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentCreateSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentDeleteSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentJoinUserSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeJoinCommentSdi;
import com.travel.vietnamtravel.dto.likecomment.sdo.LikeCommentCreateSdo;
import com.travel.vietnamtravel.dto.likecomment.sdo.LikeCommentDeleteSdo;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.Comment;
import com.travel.vietnamtravel.entity.relationship.LikeComment;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.CommentRepo;
import com.travel.vietnamtravel.repository.LikeCommentRepo;
import com.travel.vietnamtravel.service.CommentService;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.UserInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_ALREADY_EXIT;
import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;
import static com.travel.vietnamtravel.util.DataUtil.isNullObject;
import static com.travel.vietnamtravel.util.DateTimeUtils.DATE_TIME_FORMAT;
import static com.travel.vietnamtravel.util.DateTimeConvert.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepo commentRepo;
    private final LikeCommentRepo likeCommentRepo;
    private final ImageService imageService;
    private final UserInfoService userInfoService;
    private final CommonService commonService;

    public CommentCreateSdo create(CommentCreateSdi req) {

        Long loginId = commonService.getIdLogin();
        req.setCreatedBy(loginId);

        if (!isNullObject(req.getSuperCommentId())) {
            getComment(req.getSuperCommentId());
        }

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

        if (isNullObject(req.getImage()) && !isNullObject(comment.getImageId())) {
//            imageService.delete(comment.getImageId());
            comment.setImageId(null);
        } else if (!isNullObject(req.getImage())) {
            Long imageId = imageService.uploadFile(req.getImage());
            comment.setImageId(imageId);
        }

        return CommentUpdateSdo.of(Boolean.TRUE);

    }

    public CommentSelfSdo self(CommentSelfSdi req) {

        Comment comment = getComment(req.getId());
        CommentSelfSdo res = copyProperties(comment, CommentSelfSdo.class);
        res.setCreatedBy(userInfoService.shortSelf(UserInfoSelfSdi.of(comment.getCreatedBy())));

        res.setCreatedAt(dateTimeToString(comment.getCreatedAt(), DATE_TIME_FORMAT));
        res.setUpdatedAt(dateTimeToString(comment.getUpdatedAt(), DATE_TIME_FORMAT));

        Long loginId = commonService.getIdLogin();
        res.setIsLike(likeCommentRepo.existsByUserIdAndCommentId(loginId, comment.getId()));
        res.setTotalLike(likeCommentRepo.countLikeByCommentId(comment.getId()));
        res.setTotalSubComment(commentRepo.countSubComment(comment.getId()));
        return res;
    }

    public List<CommentSelfSdo> createBy(CommentJoinUserSdi req) {
        List<Long> commentsId = commentRepo.findByUserId(req.getUserId());
        return listSelf(commentsId);
    }

    public List<CommentSelfSdo> commentsInPost(CommentJoinPostSdi req) {
        List<Long> commentsId = commentRepo.findByPostID(req.getPostId());
        return listSelf(commentsId);
    }

    public List<CommentSelfSdo> subComments(CommentSelfSdi req) {
        List<Long> commentsId = commentRepo.findSubComment(req.getId());
        return listSelf(commentsId);
    }

    public LikeCommentCreateSdo like(LikeCommentCreateSdi req) {
        Long loginId = commonService.getIdLogin();
        if (likeCommentRepo.existsByUserIdAndCommentId(loginId, req.getCommentId())) {
            throw new CustomException(ERROR_ALREADY_EXIT);
        }
        LikeComment likeComment = LikeComment.builder()
                .userId(loginId)
                .commentId(req.getCommentId())
                .build();
        likeCommentRepo.save(likeComment);
        return LikeCommentCreateSdo.of(Boolean.TRUE);
    }

    public LikeCommentDeleteSdo unlike(LikeCommentDeleteSdi req) {

        Long loginId = commonService.getIdLogin();
        if (!likeCommentRepo.existsByUserIdAndCommentId(loginId, req.getCommentId())) {
            throw new CustomException(ERROR_NOT_EXIT);
        }
        LikeComment delete = likeCommentRepo.findByUserIdAndCommentId(loginId, req.getCommentId());
        likeCommentRepo.delete(delete);
        return LikeCommentDeleteSdo.of(Boolean.FALSE);
    }

    public List<UserInfoShortSelfSdo> likedBy(LikeJoinCommentSdi req) {

        List<LikeComment> likeComments = likeCommentRepo.findByCommentId(req.getCommentId());
        List<UserInfoShortSelfSdo> res = new ArrayList<>();

        likeComments.stream()
                .map(lp -> userInfoService.shortSelf(UserInfoSelfSdi.of(lp.getUserId())))
                .forEach(res::add);
        return res;
    }

    public List<CommentSelfSdo> favorites(LikeCommentJoinUserSdi req) {
        List<LikeComment> likeComments = likeCommentRepo.findByUserID(req.getUserId());
        List<CommentSelfSdo> res = new ArrayList<>();

        likeComments.stream()
                .map(lp -> self(CommentSelfSdi.of(lp.getCommentId())))
                .forEach(res::add);

        return res;
    }

    public Comment getComment(Long id) {
        return commentRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

    public List<CommentSelfSdo> listSelf(List<Long> req) {
        List<CommentSelfSdo> res = new ArrayList<>();
        req.stream()
                .map(id -> self(CommentSelfSdi.of(id)))
                .forEach(res::add);
        return res;
    }

}
