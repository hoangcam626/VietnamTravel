package com.travel.vietnamtravel.service.imp;


import com.travel.vietnamtravel.dto.comment.sdi.CommentSelfSdi;
import com.travel.vietnamtravel.dto.comment.sdo.CommentSelfSdo;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentCreateSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentDeleteSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeCommentJoinUserSdi;
import com.travel.vietnamtravel.dto.likecomment.sdi.LikeJoinCommentSdi;
import com.travel.vietnamtravel.dto.likecomment.sdo.LikeCommentCreateSdo;
import com.travel.vietnamtravel.dto.likecomment.sdo.LikeCommentDeleteSdo;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.relationship.LikeComment;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikeCommentRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.CommentService;
import com.travel.vietnamtravel.service.LikeCommentService;
import com.travel.vietnamtravel.service.UserInfoService;
import com.travel.vietnamtravel.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_ALREADY_EXIT;
import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;

@Service
@RequiredArgsConstructor
public class LikeCommentServiceImp implements LikeCommentService {
    private final LikeCommentRepo likeCommentRepo;
    private final UserInfoService userInfoService;
    private final CommentService placeService;
    private final CommonService commonService;

    public LikeCommentCreateSdo like(LikeCommentCreateSdi req) {
        req.setLikedBy(commonService.getIdLogin());
        if (likeCommentRepo.existsByUserIDAndCommentId(req.getLikedBy(), req.getCommentId())) {
//            unlike(LikedReviewDeleteSdi.of(req.getLikedBy(), req.getReviewId()));
            throw new CustomException(ERROR_ALREADY_EXIT);
        }
        LikeComment likeComment = DataUtil.copyProperties(req, LikeComment.class);
        likeCommentRepo.save(likeComment);
        return LikeCommentCreateSdo.of(likeComment.getId());
    }

    public LikeCommentDeleteSdo unlike(LikeCommentDeleteSdi req) {

        Long loginId = commonService.getIdLogin();
        LikeComment likeComment = getLikeComment(req.getId());
        if (likeComment.getUserID().equals(loginId)) {
            likeCommentRepo.delete(likeComment);
            return LikeCommentDeleteSdo.of(Boolean.TRUE);
        }
        throw new CustomException(ERROR_NOT_EXIT);

    }
    public List<UserInfoShortSelfSdo> likedBy(LikeJoinCommentSdi req) {

        List<LikeComment> likeComments = likeCommentRepo.findByCommentId(req.getCommentId());
        List<UserInfoShortSelfSdo> res = new ArrayList<>();

        likeComments.stream()
                .map(lp -> userInfoService.shortSelf(UserInfoSelfSdi.of(lp.getUserID())))
                .forEach(res::add);
        return res;
    }

    public List<CommentSelfSdo> favorites(LikeCommentJoinUserSdi req) {
        List<LikeComment> likeComments = likeCommentRepo.findByUserID(req.getUserId());
        List<CommentSelfSdo> res = new ArrayList<>();

        likeComments.stream()
                .map(lp -> placeService.self(CommentSelfSdi.of(lp.getCommentId())))
                .forEach(res::add);

        return res;
    }
    //    public Long totalLikes(LikeJoinCommentSdi req) {
//        return likeCommentRepo.countLikeByCommentId(req.getCommentId());
//    }
    public LikeComment getLikeComment(Long id) {
        return likeCommentRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }
}