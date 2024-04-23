package com.travel.vietnamtravel.dto.place.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(staticName = "of")
@Data
public class PlaceSdi {
    private String provinceCode;

    private String districtCode;

    private String wardCode;
}
