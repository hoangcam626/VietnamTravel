package com.travel.vietnamtravel.dto.administrative.provinces.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ProvinceShortSelfSdo {
    private String code;
    private String name;
}
