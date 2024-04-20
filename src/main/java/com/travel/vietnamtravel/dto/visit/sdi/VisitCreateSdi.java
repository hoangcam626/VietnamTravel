package com.travel.vietnamtravel.dto.visit.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class VisitCreateSdi {
    private Long placeId;
    private LocalDate date;
}
