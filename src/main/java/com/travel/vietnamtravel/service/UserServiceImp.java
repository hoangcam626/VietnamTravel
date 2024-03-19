package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.user.sdi.UserRegisterSdi;
import com.travel.vietnamtravel.entity.User;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImp {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User saveUser(UserRegisterSdi req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new CustomException("Error: Email is already taken!");
        }

        User user = User.builder().username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .email(req.getEmail())
//                .createTime(LocalDateTime.now())
                .build();
        user = userRepository.save(user);
        return user;
    }

    //        public void updateUser(UserRequest userRequest){
//
//        User user = getUser(userRequest.getUserId());
//
//        if (!user.getEmail().equals(userRequest.getEmail())) {
//            if (userRepository.existsByEmail(userRequest.getEmail())) {
//                throw new CustomException("Error: Email is already taken!");
//            }
//            user.setEmail(userRequest.getEmail());
//        }
//
//        if (userRequest.getAvatar() != null) {
//            Long imgId = imageService.saveUploadedFile(userRequest.getAvatar());
//            if (user.getImageId() != null) {
//                imageService.deleteImage(user.getImageId());
//            }
//            user.setImageId(imgId);
//        }
//
//        user.setUsername(userRequest.getUsername());
//        user.setPhoneNumber(userRequest.getPhoneNumber());
//
//        userRepository.save(user);
//    }
    public User getUser(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new CustomException("Error: no use"));
    }


    public void updatePassword(String prePassword, String password, Long userId) {
        User user = this.getUser(userId);
        if (!encoder.matches(prePassword, user.getPassword())) {
            throw new CustomException("Old password is not collect.");
        }
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

    public void deleteUser(Long useId) {
        userRepository.deleteById(useId);
    }
}
