package com.travel.vietnamtravel.dto.administrative.wards.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class WardShortSelfSdo {
    private Long id;
    private String code;
    private String name;
}
