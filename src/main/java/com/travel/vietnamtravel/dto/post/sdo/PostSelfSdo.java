package com.travel.vietnamtravel.dto.post.sdo;

import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PostSelfSdo {
    private Long id;

    private UserInfoShortSelfSdo createBy;

    private Long placeId;

    private Long imageId;

    private String content;

    private Timestamp createAt;

    private Timestamp updateAt;

    private Boolean isUpdate;

    private Boolean isLike;

    private Long totalLike;

    private Long totalComment;
}
