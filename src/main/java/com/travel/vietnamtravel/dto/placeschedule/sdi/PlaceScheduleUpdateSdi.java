package com.travel.vietnamtravel.dto.placeschedule.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceScheduleUpdateSdi {

    private Long id;

    private Long placeId;

    private LocalDate scheduledDate;

    private String description;

    private Boolean isComplete ;
}
