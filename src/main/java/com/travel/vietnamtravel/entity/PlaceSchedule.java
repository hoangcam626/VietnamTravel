package com.travel.vietnamtravel.entity;

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

public class PlaceSchedule extends AbstractAudit{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private ScheduleTrip schedule;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "schedule_date")
    private LocalDate scheduledDate;

    @Column(name = "description")
    private String description;
}
