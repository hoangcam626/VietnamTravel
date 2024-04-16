package com.travel.vietnamtravel.dto.administrative.administrativeunit.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UnitShortSelfSdo {
    private Long id;
    private String shortName;
}
