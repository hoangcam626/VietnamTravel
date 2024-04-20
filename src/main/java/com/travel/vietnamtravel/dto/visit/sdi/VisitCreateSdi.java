package com.travel.vietnamtravel.dto.visit.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "of")
public class VisitCreateSdi {
    private Long placeId;
    private LocalDateTime time;
}
