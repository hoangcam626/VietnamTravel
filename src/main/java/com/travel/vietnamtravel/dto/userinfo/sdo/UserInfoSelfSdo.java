package com.travel.vietnamtravel.dto.userinfo.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserInfoSelfSdo {

    private String address;
//    private LocalDate birthOfDate;
    private int level;
    private String fullName;
    private String phoneNumber;
    private Long avatarId;
    private Long userId;
    private String username;
    private String createdAt;

//    public void setUrlAvatar(long avatarId) {
//        this.urlAvatar = "/image/"+avatarId;
//    }
}
