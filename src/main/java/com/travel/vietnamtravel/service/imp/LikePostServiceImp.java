package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.likepost.sdi.LikeJoinPostSdi;
import com.travel.vietnamtravel.dto.likepost.sdi.LikePostCreateSdi;
import com.travel.vietnamtravel.dto.likepost.sdi.LikePostDeleteSdi;
import com.travel.vietnamtravel.dto.likepost.sdi.LikePostJoinUserSdi;
import com.travel.vietnamtravel.dto.likepost.sdo.LikePostCreateSdo;
import com.travel.vietnamtravel.dto.likepost.sdo.LikePostDeleteSdo;
import com.travel.vietnamtravel.dto.post.sdi.PostSelfSdi;
import com.travel.vietnamtravel.dto.post.sdo.PostSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.relationship.LikePost;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.LikePostRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.LikePostService;
import com.travel.vietnamtravel.service.PostService;
import com.travel.vietnamtravel.service.UserInfoService;
import com.travel.vietnamtravel.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.*;

@Service
@RequiredArgsConstructor
public class LikePostServiceImp implements LikePostService {
    private final LikePostRepo likePostRepo;
    private final UserInfoService userInfoService;
    private final PostService placeService;
    private final CommonService commonService;

    public LikePostCreateSdo like(LikePostCreateSdi req) {
        req.setLikedBy(commonService.getIdLogin());
        if (likePostRepo.existsByUserIDAndPostId(req.getLikedBy(), req.getPostId())) {
//            unlike(LikedReviewDeleteSdi.of(req.getLikedBy(), req.getReviewId()));
            throw new CustomException(ERROR_ALREADY_EXIT);
        }
        LikePost likePost = DataUtil.copyProperties(req, LikePost.class);
        likePostRepo.save(likePost);
        return LikePostCreateSdo.of(likePost.getId());
    }

    public LikePostDeleteSdo unlike(LikePostDeleteSdi req) {

        Long loginId = commonService.getIdLogin();
        LikePost likePost = getLikePost(req.getId());
        if (likePost.getUserID().equals(loginId)) {
            likePostRepo.delete(likePost);
            return LikePostDeleteSdo.of(Boolean.TRUE);
        }
        throw new CustomException(ERROR_NOT_EXIT);

    }

    public List<UserInfoShortSelfSdo> likedBy(LikeJoinPostSdi req) {

        List<LikePost> likePosts = likePostRepo.findByPostId(req.getPostId());
        List<UserInfoShortSelfSdo> res = new ArrayList<>();

        likePosts.stream()
                .map(lp -> userInfoService.shortSelf(UserInfoSelfSdi.of(lp.getUserID())))
                .forEach(res::add);
        return res;
    }

    public List<PostSelfSdo> favorites(LikePostJoinUserSdi req) {
        List<LikePost> likePosts = likePostRepo.findByUserID(req.getUserId());
        List<PostSelfSdo> res = new ArrayList<>();

        likePosts.stream()
                .map(lp -> placeService.self(PostSelfSdi.of(lp.getPostId())))
                .forEach(res::add);

        return res;
    }
    //    public Long totalLikes(LikeJoinPostSdi req) {
//        return likePostRepo.countLikeByPostId(req.getPostId());
//    }
    public LikePost getLikePost(Long id) {
        return likePostRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }
}
