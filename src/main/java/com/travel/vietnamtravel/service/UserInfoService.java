package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.info.sdi.UserInfoUpdateSdi;
import com.travel.vietnamtravel.dto.info.sdo.UserInfoSdo;
import com.travel.vietnamtravel.entity.UserInfo;
import com.travel.vietnamtravel.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final ImageService imageService;

    public UserInfoSdo updateUserInfo(UserInfoUpdateSdi req){
        UserInfo userInfo = UserInfo.builder()
                .userId(req.getUserId())
                .username(req.getUsername())
                .fullName(req.getFullName())
                .birthOfDate(req.getBirthOfDate())
                .address(req.getAddress())
                .build();

        return new UserInfoSdo(userInfo);
    }

    public UserInfoSdo getByUserId(Long userId){
        UserInfo userInfo = userInfoRepository.findByUserId(userId);
        return new UserInfoSdo(userInfo);
    }

    public UserInfoSdo setAvatar(Long userId, MultipartFile image){
        Long avatarId = imageService.saveUploadedFile(image);
        UserInfo userInfo = userInfoRepository.findByUserId(userId);
        userInfo.setAvatarId(avatarId);
        return new UserInfoSdo(userInfo);
    }

}
