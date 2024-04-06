package com.travel.vietnamtravel.dto.review.sdo;

import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class ReviewSelfSdo {

    private UserInfoShortSelfSdo createBy;

    private Long placeId;

    private int rating;

    private String description;

    private List<Long> imagesId;

    private Timestamp createAt;

    private Timestamp updatedAt;

    private Long totalLike;
}
