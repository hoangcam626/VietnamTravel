package com.travel.vietnamtravel.dto.info.sdo;

import com.travel.vietnamtravel.entity.UserInfo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class UserInfoSdo {

    private String address;
    private LocalDate birthOfDate;
    private int level;
    private String fullName;
    private String phoneNumber;
    private String urlAvatar;
    private Long userId;
    private String username;

    public UserInfoSdo(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.username = userInfo.getUsername();
        this.fullName = userInfo.getFullName();
        this.address = userInfo.getAddress();
        this.birthOfDate = userInfo.getBirthOfDate();
        this.level = userInfo.getLevel();
        this.phoneNumber = userInfo.getPhoneNumber();
        if (userInfo.getAvatarId() != null) {
            this.urlAvatar = "" + userInfo.getAvatarId();
        }
    }
}
