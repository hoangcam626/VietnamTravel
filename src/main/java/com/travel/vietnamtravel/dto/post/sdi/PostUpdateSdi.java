package com.travel.vietnamtravel.dto.post.sdi;

import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor(staticName = "of")
public class PostUpdateSdi {

    private MultipartFile image;

    private String content;
}
