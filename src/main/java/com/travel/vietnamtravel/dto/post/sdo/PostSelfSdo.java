package com.travel.vietnamtravel.dto.post.sdo;

import com.travel.vietnamtravel.dto.place.sdo.PlaceShortSelfSdo;
import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PostSelfSdo {
    private Long id;

    private UserInfoShortSelfSdo createBy;

    private PlaceShortSelfSdo place;

    private Long imageId;

    private String content;

    private String createdAt;

    private String updatedAt;

    private Boolean isUpdate;

    private Boolean isLike;

    private Long totalLike;

    private Long totalComment;
}
