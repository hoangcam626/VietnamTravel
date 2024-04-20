package com.travel.vietnamtravel.dto.likereview.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class LikeReviewCreateSdo {
    private Boolean isLike;

}
