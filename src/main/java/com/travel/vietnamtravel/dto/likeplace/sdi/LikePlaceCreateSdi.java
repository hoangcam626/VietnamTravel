package com.travel.vietnamtravel.dto.likeplace.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class LikePlaceCreateSdi {
    private Long likedBy;
    private Long placeId;
}
