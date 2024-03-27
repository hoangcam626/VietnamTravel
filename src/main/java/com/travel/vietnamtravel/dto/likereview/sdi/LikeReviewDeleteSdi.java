package com.travel.vietnamtravel.dto.likereview.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class LikeReviewDeleteSdi {
    private Long id;
    private Long likedBy;
    private Long reviewId;
}
