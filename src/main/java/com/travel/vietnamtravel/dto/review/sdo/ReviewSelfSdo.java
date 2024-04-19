package com.travel.vietnamtravel.dto.review.sdo;

import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ReviewSelfSdo {

    private UserInfoShortSelfSdo createBy;

    private Long placeId;

    private int rating;

    private String description;

    private List<Long> imagesId;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private Long totalLike;

    private Boolean isLike;
}
