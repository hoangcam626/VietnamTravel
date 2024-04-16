package com.travel.vietnamtravel.dto.administrative.wards.sdo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class WardSelfSdo {
    private Long id;
    private String name;

    private String code;

    private String nameEn;

    private String fullName;

    private String fullNameEn;

    private String codeName;

    private String districtCode;

    private Long administrativeUnitId;
}
