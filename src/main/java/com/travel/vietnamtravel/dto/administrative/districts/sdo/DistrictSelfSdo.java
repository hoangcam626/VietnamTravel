package com.travel.vietnamtravel.dto.administrative.districts.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DistrictSelfSdo {

    private Long id;

    private String code;

    private String name;

    private String nameEn;

    private String fullName;

    private String fullNameEn;

    private String codeName;

    private String provinceCode;

    private Long administrativeUnitId;
}
