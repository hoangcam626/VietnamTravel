package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoUpdateSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoUpdateSdo;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService {
    UserInfoUpdateSdo update(UserInfoUpdateSdi req);
    UserInfoUpdateSdo updateAvatar(Long userId, MultipartFile image);
    UserInfoSelfSdo self(UserInfoSelfSdi req);
    UserInfoShortSelfSdo shortSelf(UserInfoSelfSdi req);
}
