package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.user.sdi.UserLoginSdi;
import com.travel.vietnamtravel.dto.user.sdo.UserLoginSdo;
import com.travel.vietnamtravel.security.JwtUtils;
import com.travel.vietnamtravel.security.UserDetailsImpl;
import com.travel.vietnamtravel.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils tokenProvider;
    public UserLoginSdo login (UserLoginSdi req){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = tokenProvider.generateJwtToken(authentication);
        return new UserLoginSdo(jwt, userDetails.getId(), userDetails.getUsername());
    }
}
