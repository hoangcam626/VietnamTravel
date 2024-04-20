package com.travel.vietnamtravel.dto.placeschedule.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceScheduleCreateSdi {

    private Long scheduleId;

    private Long placeId;

    private LocalDate scheduleDate;

    private LocalTime scheduleBeginTime;

    private LocalTime scheduleFinishTime;

    private String transport;

    private String description;

    private Boolean isComplete ;
}
