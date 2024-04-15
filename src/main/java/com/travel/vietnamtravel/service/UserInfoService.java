package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.userinfo.sdi.*;
import com.travel.vietnamtravel.dto.userinfo.sdo.*;

public interface UserInfoService {
    UserInfoUpdateSdo update(UserInfoUpdateSdi req);
    UserInfoUpdateSdo updateAvatar(UserInfoUpdateAvatarSdi req);
    UserInfoSelfSdo self(UserInfoSelfSdi req);
    UserInfoShortSelfSdo shortSelf(UserInfoSelfSdi req);
}
