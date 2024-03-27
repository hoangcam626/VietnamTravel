package com.travel.vietnamtravel.dto.userinfo.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserInfoShortSelfSdo {
    private Long userId;
    private String username;
    private Long avatarId;
}
