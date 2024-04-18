package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.userinfo.sdi.*;
import com.travel.vietnamtravel.dto.userinfo.sdo.*;
import com.travel.vietnamtravel.entity.User;
import com.travel.vietnamtravel.entity.UserInfo;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.UserInfoRepo;
import com.travel.vietnamtravel.repository.UserRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.travel.vietnamtravel.constant.Error.*;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImp implements UserInfoService {

    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;
    private final ImageService imageService;
    private final CommonService commonService;

    public UserInfoUpdateSdo update(UserInfoUpdateSdi req) {
        Long loginId = commonService.getIdLogin();

        UserInfo userInfo = userInfoRepo.findByUserId(loginId);

        String address = req.getAddress();
        LocalDate birthOfDate = req.getBirthOfDate();
        String fullName = req.getFullName();
        String phoneNumber = req.getPhoneNumber();

        userInfo.setFullName(fullName);
        userInfo.setBirthOfDate(birthOfDate);
        userInfo.setAddress(address);
        userInfo.setPhoneNumber(phoneNumber);
        userInfoRepo.save(userInfo);

        User user = getUser(req.getUserId());
        user.setUsername(req.getUsername());
        userRepo.save(user);

        return UserInfoUpdateSdo.of(Boolean.TRUE);
    }


    public UserInfoUpdateSdo updateAvatar(UserInfoUpdateAvatarSdi req) {
        Long loginId = commonService.getIdLogin();

        UserInfo userInfo = userInfoRepo.findByUserId(loginId);

//        if (userInfo.getAvatarId() != null) {
//            imageService.delete(userInfo.getAvatarId());
//        }

        Long avatarId = imageService.uploadFile(req.getImage());
        userInfo.setAvatarId(avatarId);

        userInfoRepo.save(userInfo);

        return UserInfoUpdateSdo.of(Boolean.TRUE);
    }

    public UserInfoSelfSdo self(UserInfoSelfSdi req) {

        UserInfo userInfo = userInfoRepo.findByUserId(req.getUserId());
        UserInfoSelfSdo userInfoSelfSdo = copyProperties(userInfo, UserInfoSelfSdo.class);

        userInfoSelfSdo.setUsername(getUser(userInfo.getUserId()).getUsername());

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

    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }
}
