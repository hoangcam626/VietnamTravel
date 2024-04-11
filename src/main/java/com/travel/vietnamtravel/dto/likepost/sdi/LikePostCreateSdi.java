package com.travel.vietnamtravel.dto.likepost.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class LikePostCreateSdi {
    private Long likedBy;
    private Long postId;
}
