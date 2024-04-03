package com.travel.vietnamtravel.entity.relationship;

import com.travel.vietnamtravel.entity.AbstractAudit;
import com.travel.vietnamtravel.entity.Place;
import com.travel.vietnamtravel.entity.ScheduleTrip;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "place_schudule")

public class PlaceSchedule extends AbstractAudit {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "schedule_id")
    private Long placeId;

    @Column(name = "schedule_date")
    private LocalDate scheduledDate;

    @Column(name = "description")
    private String description;

    private Boolean isComplete ;
}
