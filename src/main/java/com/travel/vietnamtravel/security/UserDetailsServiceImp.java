package com.travel.vietnamtravel.security;

import com.travel.vietnamtravel.entity.User;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepo userRepository;
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new CustomException("User not found with username: " + username);
        }

        return UserDetailsImpl.build(user);
    }
}