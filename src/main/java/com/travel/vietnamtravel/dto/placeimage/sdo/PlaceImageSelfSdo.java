package com.travel.vietnamtravel.dto.placeimage.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PlaceImageSelfSdo {
    private Long id;
    private Long placeId;
    private Long imageId;
}
