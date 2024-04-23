package com.travel.vietnamtravel.dto.scheduletrip.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class ScheduleTripCreateSdi {

    private String nameSchedule;

    private MultipartFile imageLabel;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

}
