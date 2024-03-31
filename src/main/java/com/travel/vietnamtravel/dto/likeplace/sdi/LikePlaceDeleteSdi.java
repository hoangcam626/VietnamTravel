package com.travel.vietnamtravel.dto.likeplace.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class LikePlaceDeleteSdi {
    private Long id;
    private Long userId;
    private Long placeId;
}
