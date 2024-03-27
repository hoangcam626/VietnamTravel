package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoUpdateSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoUpdateSdo;
import com.travel.vietnamtravel.entity.User;
import com.travel.vietnamtravel.entity.UserInfo;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.UserInfoRepo;
import com.travel.vietnamtravel.repository.UserRepo;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import static com.travel.vietnamtravel.constant.Error.*;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImp implements UserInfoService {

    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;
    private final ImageService imageService;

    public UserInfoUpdateSdo update(UserInfoUpdateSdi req) {
        UserInfo userInfo = userInfoRepo.findByUserId(req.getUserId());

        String address = req.getAddress();
        LocalDate birthOfDate = req.getBirthOfDate();
        String fullName = req.getFullName();
        String phoneNumber = req.getPhoneNumber();

        userInfo.setFullName(fullName);
        userInfo.setBirthOfDate(birthOfDate);
        userInfo.setAddress(address);
        userInfo.setPhoneNumber(phoneNumber);
        userInfoRepo.save(userInfo);

        User user = userRepo.findById(req.getUserId()).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
        user.setUsername(req.getUsername());
        userRepo.save(user);

        return UserInfoUpdateSdo.of(Boolean.TRUE);
    }


    public UserInfoUpdateSdo updateAvatar(Long userId, MultipartFile image) {

        UserInfo userInfo = userInfoRepo.findByUserId(userId);

        if (userInfo.getAvatarId() != null) {
            imageService.delete(userInfo.getAvatarId());
        }

        Long avatarId = imageService.uploadFile(image);
        userInfo.setAvatarId(avatarId);

        userInfoRepo.save(userInfo);

        return UserInfoUpdateSdo.of(Boolean.TRUE);
    }

    public UserInfoSelfSdo self(UserInfoSelfSdi req) {

        UserInfo userInfo = userInfoRepo.findByUserId(req.getUserId());
        UserInfoSelfSdo userInfoSelfSdo = copyProperties(userInfo, UserInfoSelfSdo.class);

        userInfoSelfSdo.setUrlAvatar(userInfo.getAvatarId());

        User user = userRepo.findById(userInfo.getUserId()).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
        userInfoSelfSdo.setUsername(user.getUsername());

        return userInfoSelfSdo;
    }

    public UserInfoShortSelfSdo shortSelf(UserInfoSelfSdi req) {
        User user = userRepo.findById(req.getUserId()).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
        UserInfo userInfo = userInfoRepo.findByUserId(req.getUserId());
        return UserInfoShortSelfSdo.of(userInfo.getUserId(), user.getUsername(), userInfo.getAvatarId());
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoRepo.findByUserId(userId);
    }
}
