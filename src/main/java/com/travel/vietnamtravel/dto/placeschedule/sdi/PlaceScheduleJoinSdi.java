package com.travel.vietnamtravel.dto.placeschedule.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceScheduleJoinSdi {

    private Long scheduleId;
    private LocalDate date;
}
