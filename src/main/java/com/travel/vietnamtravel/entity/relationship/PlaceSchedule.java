package com.travel.vietnamtravel.entity.relationship;

import com.travel.vietnamtravel.entity.AbstractAudit;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "place_schudule")

public class PlaceSchedule extends AbstractAudit {

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "schedule_date")
    private LocalDate scheduleDate;

    @Column(name = "description")
    private String description;

    @Column(name = "is_complete")
    private Boolean isComplete ;

    @Column(name = "begin_time")
    private LocalTime scheduleBeginTime;

    @Column(name = "finish_time")
    private LocalTime scheduleFinishTime;

    @Column(name = "tranport")
    private String transport;
}
