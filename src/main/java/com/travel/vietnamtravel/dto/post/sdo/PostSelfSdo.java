package com.travel.vietnamtravel.dto.post.sdo;

import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PostSelfSdo {
    private Long id;

    private UserInfoShortSelfSdo createBy;

    private Long placeId;

    private Long imageId;

    private String content;
}
