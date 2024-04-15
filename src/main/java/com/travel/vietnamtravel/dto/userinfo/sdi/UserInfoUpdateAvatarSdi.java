package com.travel.vietnamtravel.dto.userinfo.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor(staticName = "of")
public class UserInfoUpdateAvatarSdi {
    private Long userId;
    private MultipartFile image;
}
