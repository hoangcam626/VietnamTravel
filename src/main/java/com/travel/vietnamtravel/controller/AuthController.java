package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.user.sdi.UserLoginSdi;
import com.travel.vietnamtravel.dto.user.sdi.UserRegisterSdi;
import com.travel.vietnamtravel.dto.user.sdo.UserLoginSdo;
import com.travel.vietnamtravel.dto.user.sdo.UserRegisterSdo;
import com.travel.vietnamtravel.service.AuthService;
import com.travel.vietnamtravel.service.imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserServiceImp userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginSdo> login(@RequestBody UserLoginSdi req) {

        UserLoginSdo userLoginSdo = authService.login(req);
        return ResponseEntity.ok(userLoginSdo);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterSdo> register(@RequestBody UserRegisterSdi req) {

        UserRegisterSdo userRegisterSdo = userService.register(req);
        return ResponseEntity.ok(userRegisterSdo);
    }
}
