package com.travel.vietnamtravel.dto.scheduletrip.sdo;

import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ScheduleTripSelfSdo {

    private Long id;

    private String nameSchedule;

    private Long imageLabelId;

    private String description;

    private String startDate;

    private String endDate;

    private List<PlaceScheduleSelfSdo> places;

    private String createdAt;

    private String updatedAt;
}
