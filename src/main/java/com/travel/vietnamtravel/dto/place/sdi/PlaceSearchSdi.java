package com.travel.vietnamtravel.dto.place.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceSearchSdi {
    private String keyword;
}
