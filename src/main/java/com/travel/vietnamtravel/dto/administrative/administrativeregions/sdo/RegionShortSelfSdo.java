package com.travel.vietnamtravel.dto.administrative.administrativeregions.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class RegionShortSelfSdo {
    private Long id;
    private String name;
}
