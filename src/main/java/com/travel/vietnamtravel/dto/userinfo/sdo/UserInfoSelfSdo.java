package com.travel.vietnamtravel.dto.userinfo.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class UserInfoSelfSdo {

    private String address;
    private LocalDate birthOfDate;
    private int level;
    private String fullName;
    private String phoneNumber;
    private String urlAvatar;
    private Long userId;
    private String username;

//    public void setUrlAvatar(long avatarId) {
//        this.urlAvatar = "/image/"+avatarId;
//    }
}
