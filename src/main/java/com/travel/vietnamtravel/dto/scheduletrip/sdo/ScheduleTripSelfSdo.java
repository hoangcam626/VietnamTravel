package com.travel.vietnamtravel.dto.scheduletrip.sdo;

import com.travel.vietnamtravel.dto.placeschedule.sdo.PlaceScheduleSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class ScheduleTripSelfSdo {
    private String nameSchedule;

    private MultipartFile imageLabel;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;
    private List<PlaceScheduleSelfSdo> places;
}
