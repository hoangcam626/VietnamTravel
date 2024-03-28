package com.travel.vietnamtravel.dto.administrative.provinces.sdo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ProvinceSelfSdo {
    private Long id;

    private String code;

    private String name;

    private String nameEn;

    private String fullName;

    private String fullNameEn;

    private String codeName;

    private Long administrativeUnitId;

    private Long administrativeRegionId;
}
