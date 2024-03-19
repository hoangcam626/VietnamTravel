package com.travel.vietnamtravel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name_schedule")
    private String nameSchedule;

    @Column(name = "image_label_id")
    private Long imageLabelId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(name = "schedule_place",
            joinColumns = @JoinColumn(name = "travel_schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id"))
    private Set<Place> likedPlaces;
}
