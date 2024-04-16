package com.travel.vietnamtravel.dto.administrative.districts.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DistrictShortSelfSdo {
    private String code;
    private String name;
}
