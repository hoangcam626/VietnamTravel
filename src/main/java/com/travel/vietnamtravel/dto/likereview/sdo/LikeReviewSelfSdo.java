package com.travel.vietnamtravel.dto.likereview.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class LikeReviewSelfSdo {
    private Long id;
    private Long userId;
    private Long reviewId;
}
