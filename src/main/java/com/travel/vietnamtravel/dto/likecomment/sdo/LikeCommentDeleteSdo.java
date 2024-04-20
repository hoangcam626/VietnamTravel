package com.travel.vietnamtravel.dto.likecomment.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class LikeCommentDeleteSdo {
    private Boolean isLike;
}
