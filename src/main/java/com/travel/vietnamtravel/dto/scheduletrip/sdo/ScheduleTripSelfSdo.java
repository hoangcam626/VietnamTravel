package com.travel.vietnamtravel.dto.scheduletrip.sdo;

import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ScheduleTripSelfSdo {

    private String nameSchedule;

    private MultipartFile imageLabel;

    private String description;

    private String startDate;

    private String endDate;

    private List<PlaceScheduleSelfSdo> places;

    private String createdAt;

    private String updatedAt;
}
