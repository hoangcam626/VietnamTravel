package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoSelfSdi;
import com.travel.vietnamtravel.dto.userinfo.sdi.UserInfoUpdateSdi;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoUpdateSdo;
import com.travel.vietnamtravel.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserInfoService userInfoService;

    @PutMapping
    public ResponseEntity<UserInfoUpdateSdo> update (UserInfoUpdateSdi req){
        return ResponseEntity.ok(userInfoService.update(req));
    }
    @GetMapping("/self")
    public  ResponseEntity<UserInfoSelfSdo> self(UserInfoSelfSdi req){
        return ResponseEntity.ok(userInfoService.self(req));
    }
    @GetMapping("/short-self")
    public  ResponseEntity<UserInfoShortSelfSdo> shortSelf(UserInfoSelfSdi req){
        return ResponseEntity.ok(userInfoService.shortSelf(req));
    }
}
