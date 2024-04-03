package com.travel.vietnamtravel.dto.placeschedule.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceScheduleSelfSdo {

    private Long id;

    private Long scheduleId;

    private Long placeId;

    private LocalDate scheduledDate;

    private String description;

    private Boolean isComplete ;
}
