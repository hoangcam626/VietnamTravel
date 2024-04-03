package com.travel.vietnamtravel.dto.placeschedule.sdi;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceScheduleCreateSdi {

    private Long scheduleId;

    private Long placeId;

    private LocalDate scheduledDate;

    private String description;

    private Boolean isComplete ;
}
