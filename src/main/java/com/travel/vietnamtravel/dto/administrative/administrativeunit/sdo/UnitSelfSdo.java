package com.travel.vietnamtravel.dto.administrative.administrativeunit.sdo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UnitSelfSdo {

    private Long id;

    private String fullName;

    private String fullNameEn;

    private String shortName;

    private String shortNameEn;

    private String code;

    private String codeEn;

}
