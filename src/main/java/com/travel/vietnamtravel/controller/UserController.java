package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.user.sdi.UserLoginSdi;
import com.travel.vietnamtravel.dto.user.sdi.UserRegisterSdi;
import com.travel.vietnamtravel.dto.user.sdo.UserLoginSdo;
import com.travel.vietnamtravel.entity.User;
import com.travel.vietnamtravel.security.JwtUtils;
import com.travel.vietnamtravel.security.UserDetailsImpl;
import com.travel.vietnamtravel.service.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils tokenProvider;
    private final UserServiceImp userService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<UserLoginSdo> login(@RequestBody UserLoginSdi req) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = tokenProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new UserLoginSdo(jwt, userDetails.getId(), userDetails.getUsername()));
    }

    @PostMapping("api/auth/register")
    public ResponseEntity register(@RequestBody UserRegisterSdi req) {

        User user = userService.saveUser(req);

        return ResponseEntity.ok().build();
    }
}
