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
@Table(name = "schedule_trip")
public class ScheduleTrip extends AbstractAudit{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "user_id")
    private Long createdBy;

    @Column(name = "name_schedule")
    private String nameSchedule;

    @Column(name = "image_label_id")
    private Long imageLabelId;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

//    @Column(name = "created_at")
//    private LocalDateTime createAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
}
