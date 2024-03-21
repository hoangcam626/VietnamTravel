package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.info.sdi.UserInfoUpdateSdi;
import com.travel.vietnamtravel.dto.info.sdo.UserInfoSdo;
import com.travel.vietnamtravel.entity.User;
import com.travel.vietnamtravel.entity.UserInfo;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.UserInfoRepository;
import com.travel.vietnamtravel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private  final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final ImageService imageService;

    public UserInfoSdo updateUserInfo(UserInfoUpdateSdi req){
        UserInfo userInfo = userInfoRepository.findByUserId(req.getUserId());

        userInfo.setFullName(req.getFullName());
        userInfo.setBirthOfDate(req.getBirthOfDate());
        userInfo.setAddress(req.getAddress());
        userInfo.setPhoneNumber(req.getPhoneNumber());
        userInfoRepository.save(userInfo);

        User user = userRepository.findById(req.getUserId()).orElseThrow(()-> new CustomException(""));
        user.setUsername(req.getUsername());
        userRepository.save(user);

        return new UserInfoSdo(userInfo);
    }

    public UserInfoSdo getByUserId(Long userId){

        UserInfo userInfo = userInfoRepository.findByUserId(userId);
        return new UserInfoSdo(userInfo);
    }

    public UserInfoSdo setAvatar(Long userId, MultipartFile image){
        UserInfo userInfo = userInfoRepository.findByUserId(userId);

        if (userInfo.getAvatarId() != null) {
                imageService.deleteImage(userInfo.getAvatarId());
        }

        Long avatarId = imageService.uploadFile(image);
        userInfo.setAvatarId(avatarId);

        userInfoRepository.save(userInfo);

        return new UserInfoSdo(userInfo);
    }

}
