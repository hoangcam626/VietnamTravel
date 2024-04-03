package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.post.sdi.*;
import com.travel.vietnamtravel.dto.post.sdo.PostCreateSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostDeleteSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostSelfSdo;
import com.travel.vietnamtravel.dto.post.sdo.PostUpdateSdo;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.entity.PostImage;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.PostImageRepo;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.PostService;
import com.travel.vietnamtravel.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImp implements PostService {
    private final PostImageRepo postImageRepo;
    private final ImageService imageService;
    private final UserInfoService userInfoService;
    public PostCreateSdo create(PostCreateSdi req){
        PostImage postImage = copyProperties(req, PostImage.class);
        Long imageId = imageService.uploadFile(req.getImage());
        postImage.setImageId(imageId);
        postImageRepo.save(postImage);
        return PostCreateSdo.of(postImage.getId());
    }
    public PostDeleteSdo delete(PostDeleteSdi req){
        PostImage postImage = getPost(req.getId());
        imageService.delete(postImage.getImageId());
        postImageRepo.delete(postImage);
        return PostDeleteSdo.of(Boolean.TRUE);
    }
    public PostUpdateSdo update(PostUpdateSdi req){
        PostImage postImage = getPost(req.getId());
        postImage.setContent(req.getContent());
        postImage.setPlaceId(req.getPlaceId());
        postImageRepo.save(postImage);
        return PostUpdateSdo.of(Boolean.TRUE);
    }
    public PostSelfSdo self(PostSelfSdi req){
        PostImage postImage = getPost(req.getId());
        PostSelfSdo res = copyProperties(req, PostSelfSdo.class);
        UserInfoShortSelfSdo userInfoShortSelf = userInfoService.shortSelf(UserInfoSelfSdi.of(postImage.getCreatedBy()));
        res.setCreateBy(userInfoShortSelf);
        return res;
    }

    public List<PostSelfSdo> allPosts(){
        List<Long> postImageIds = postImageRepo.findAllOrderByCreatAt();
        return listSelf(postImageIds);
    }
    public List<PostSelfSdo> createBy(PostJoinUserSdi req){
        List<Long> postImageIds = postImageRepo.findByUserId(req.getUserId());
        return listSelf(postImageIds);
    }public List<PostSelfSdo> postsInPlace(PostJoinPlaceSdi req){
        List<Long> postImageIds = postImageRepo.findByPlaceId(req.getPlaceId());
        return listSelf(postImageIds);
    }

    public PostImage getPost(Long id){
        return postImageRepo.findById(id).orElseThrow(()-> new CustomException(ERROR_NOT_EXIT));
    }
    public List<PostSelfSdo> listSelf(List<Long> req){
        List<PostSelfSdo> res = new ArrayList<>();
        req.stream()
                .map(id -> self(PostSelfSdi.of(id)))
                .forEach(res::add);
        return res;
    }
}
