package com.travel.vietnamtravel.dto.administrative.wards.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class WardShortSelfSdo {
    private String code;
    private String name;
}
