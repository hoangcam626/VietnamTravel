package com.travel.vietnamtravel.dto.placeimage.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceImageUpdateSdi {
    private Long id;
    private Long placeId;
}