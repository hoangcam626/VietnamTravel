package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.user.sdi.UserRegisterSdi;
import com.travel.vietnamtravel.dto.user.sdo.UserRegisterSdo;
import com.travel.vietnamtravel.entity.User;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.UserRepo;
import com.travel.vietnamtravel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.travel.vietnamtravel.constant.Error.*;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepo userRepository;
    private final PasswordEncoder encoder;

    public UserRegisterSdo register(UserRegisterSdi req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new CustomException(ERROR_EXIT_EMAIL);
        }

        User user = copyProperties(req, User.class);
        String password = encoder.encode(req.getPassword());
        user.setPassword(password);
        userRepository.save(user);

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

    public void delete(Long useId) {
        userRepository.deleteById(useId);
    }
}
