package com.travel.vietnamtravel.dto.placeimage.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceImageJoinSdi {
    private Long placeId;
    private Long imageId;
}
