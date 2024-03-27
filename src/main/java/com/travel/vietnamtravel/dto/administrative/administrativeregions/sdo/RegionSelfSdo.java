package com.travel.vietnamtravel.dto.administrative.administrativeregions.sdo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class RegionSelfSdo {

    private Long id;

    private String name;

    private String nameEn;

    private String code;

    private String codeEn;
}
