package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.user.sdi.UserRegisterSdi;
import com.travel.vietnamtravel.dto.user.sdo.UserRegisterSdo;
import com.travel.vietnamtravel.entity.User;
public interface UserService {

    UserRegisterSdo register(UserRegisterSdi req);

    User getUser(Long id);

    void updatePassword(String prePassword, String password, Long userId);

    void delete(Long useId);
}
