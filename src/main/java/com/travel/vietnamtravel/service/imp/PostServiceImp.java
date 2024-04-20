package com.travel.vietnamtravel.service.imp;

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
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.Post;
import com.travel.vietnamtravel.entity.relationship.LikePost;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.CommentRepo;
import com.travel.vietnamtravel.repository.LikePostRepo;
import com.travel.vietnamtravel.repository.PostRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.PostService;
import com.travel.vietnamtravel.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_ALREADY_EXIT;
import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;
import static com.travel.vietnamtravel.util.DateTimeUtils.DATE_TIME_FORMAT;
import static com.travel.vietnamtravel.util.DateTimeConvert.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImp implements PostService {
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final LikePostRepo likePostRepo;
    private final ImageService imageService;
    private final UserInfoService userInfoService;
    private final CommonService commonService;

    public PostCreateSdo create(PostCreateSdi req){
        Long loginId = commonService.getIdLogin();
        req.setCreatedBy(loginId);
        Post postImage = copyProperties(req, Post.class);
        Long imageId = imageService.uploadFile(req.getImage());
        postImage.setImageId(imageId);
        postRepo.save(postImage);
        return PostCreateSdo.of(postImage.getId());
    }
    public PostDeleteSdo delete(PostDeleteSdi req){
        Post postImage = getPost(req.getId());
        imageService.delete(postImage.getImageId());
        postRepo.delete(postImage);
        return PostDeleteSdo.of(Boolean.TRUE);
    }
    public PostUpdateSdo update(PostUpdateSdi req){
        Post postImage = getPost(req.getId());
        postImage.setContent(req.getContent());
        postImage.setPlaceId(req.getPlaceId());
        postRepo.save(postImage);
        return PostUpdateSdo.of(Boolean.TRUE);
    }
    public PostSelfSdo self(PostSelfSdi req){

        Post post = getPost(req.getId());
        PostSelfSdo res = copyProperties(post, PostSelfSdo.class);

        UserInfoShortSelfSdo userInfoShortSelf = userInfoService.shortSelf(UserInfoSelfSdi.of(post.getCreatedBy()));
        res.setCreateBy(userInfoShortSelf);

        Long loginId = commonService.getIdLogin();
        res.setIsLike(likePostRepo.existsByUserIdAndPostId(loginId, post.getId()));
        res.setTotalLike(likePostRepo.countLikeByPostId(post.getId()));
        res.setTotalComment(commentRepo.countComment(post.getId()));
        res.setCreatedAt(dateTimeToString(post.getCreatedAt(), DATE_TIME_FORMAT));
        res.setUpdatedAt(dateTimeToString(post.getUpdatedAt(), DATE_TIME_FORMAT));

        return res;
    }

    public List<PostSelfSdo> allPosts(){
        List<Long> postImageIds = postRepo.findAllOrderByCreatAt();
        return listSelf(postImageIds);
    }
    public List<PostSelfSdo> createBy(PostJoinUserSdi req){
        List<Long> postImageIds = postRepo.findByUserId(req.getUserId());
        return listSelf(postImageIds);
    }public List<PostSelfSdo> postsInPlace(PostJoinPlaceSdi req){
        List<Long> postImageIds = postRepo.findByPlaceId(req.getPlaceId());
        return listSelf(postImageIds);
    }
    public LikePostCreateSdo like(LikePostCreateSdi req) {
        Long loginId = commonService.getIdLogin();
        if (likePostRepo.existsByUserIdAndPostId(loginId, req.getPostId())) {
            throw new CustomException(ERROR_ALREADY_EXIT);
        }
        LikePost likePost = LikePost.builder()
                .userId(loginId)
                .postId(req.getPostId())
                .build();
        likePostRepo.save(likePost);
        return LikePostCreateSdo.of(likePost.getId());
    }

    public LikePostDeleteSdo unlike(LikePostDeleteSdi req) {

        Long loginId = commonService.getIdLogin();
        if (!likePostRepo.existsByUserIdAndPostId(loginId, req.getPostId())) {
            throw new CustomException(ERROR_NOT_EXIT);
        }
        LikePost delete = likePostRepo.findByUserIdAndPostId(loginId, req.getPostId());
        likePostRepo.delete(delete);
        return LikePostDeleteSdo.of(Boolean.TRUE);
    }

    public List<UserInfoShortSelfSdo> likedBy(LikeJoinPostSdi req) {

        List<LikePost> likePosts = likePostRepo.findByPostId(req.getPostId());
        List<UserInfoShortSelfSdo> res = new ArrayList<>();

        likePosts.stream()
                .map(lp -> userInfoService.shortSelf(UserInfoSelfSdi.of(lp.getUserId())))
                .forEach(res::add);
        return res;
    }

    public List<PostSelfSdo> favorites(LikePostJoinUserSdi req) {
        List<LikePost> likePosts = likePostRepo.findByUserID(req.getUserId());
        List<PostSelfSdo> res = new ArrayList<>();

        likePosts.stream()
                .map(lp -> self(PostSelfSdi.of(lp.getPostId())))
                .forEach(res::add);

        return res;
    }

    public Post getPost(Long id){
        return postRepo.findById(id).orElseThrow(()-> new CustomException(ERROR_NOT_EXIT));
    }
    public List<PostSelfSdo> listSelf(List<Long> req){
        List<PostSelfSdo> res = new ArrayList<>();
        req.stream()
                .map(id -> self(PostSelfSdi.of(id)))
                .forEach(res::add);
        return res;
    }
}
