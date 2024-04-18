package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.user.sdi.UserLoginSdi;
import com.travel.vietnamtravel.dto.user.sdi.UserRegisterSdi;
import com.travel.vietnamtravel.dto.user.sdo.UserLoginSdo;
import com.travel.vietnamtravel.dto.user.sdo.UserRegisterSdo;
import com.travel.vietnamtravel.entity.User;
import com.travel.vietnamtravel.entity.UserInfo;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.UserInfoRepo;
import com.travel.vietnamtravel.repository.UserRepo;
import com.travel.vietnamtravel.security.JwtUtils;
import com.travel.vietnamtravel.security.UserDetailsImpl;
import com.travel.vietnamtravel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.travel.vietnamtravel.constant.Error.*;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepo userRepository;
    private final UserInfoRepo userInfoRepo;
    private final PasswordEncoder encoder;

    public UserRegisterSdo register(UserRegisterSdi req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new CustomException(ERROR_EXIT_EMAIL);
        }

        User user = copyProperties(req, User.class);
        String password = encoder.encode(req.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        UserInfo userInfo = UserInfo.builder().userId(user.getId()).build();
        userInfoRepo.save(userInfo);
        return UserRegisterSdo.of(user.getId());
    }

    public User getUser(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new CustomException("Error: no use"));
    }

    public void updatePassword(String prePassword, String password, Long userId) {

        User user = this.getUser(userId);

        if (!encoder.matches(prePassword, user.getPassword())) {
            throw new CustomException(ERROR_OLD_PASSWORD);
        }

        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }
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

    public void delete(Long useId) {
        userRepository.deleteById(useId);
    }
}
