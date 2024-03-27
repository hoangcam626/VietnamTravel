package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.user.sdi.UserLoginSdi;
import com.travel.vietnamtravel.dto.user.sdo.UserLoginSdo;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserLoginSdo login (UserLoginSdi req);
}
