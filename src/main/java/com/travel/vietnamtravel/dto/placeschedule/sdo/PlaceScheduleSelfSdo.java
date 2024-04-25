package com.travel.vietnamtravel.dto.placeschedule.sdo;

import com.travel.vietnamtravel.dto.place.sdo.PlaceSelfSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PlaceScheduleSelfSdo {

    private Long id;

    private Long scheduleId;

    private PlaceShortSelfSdo place;

    private String scheduledDate;

    private String scheduleBeginTime;

    private String scheduleFinishTime;

    private String transport;

    private String description;

    private Boolean isComplete;

    private String createdAt;

    private String updatedAt;

}
