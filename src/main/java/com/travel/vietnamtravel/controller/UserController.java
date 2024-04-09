package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoUpdateSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoUpdateSdo;
import com.travel.vietnamtravel.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserInfoService userInfoService;

    @PutMapping
    public ResponseEntity<UserInfoUpdateSdo> update (UserInfoUpdateSdi req){
        return ResponseEntity.ok(userInfoService.update(req));
    }
}
