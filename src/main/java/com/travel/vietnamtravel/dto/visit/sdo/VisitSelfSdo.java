package com.travel.vietnamtravel.dto.visit.sdo;

import com.travel.vietnamtravel.dto.place.sdo.PlaceSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class VisitSelfSdo {
    private Long id;
    private Long userId;
    private PlaceSelfSdo place;
    private String date;
}
