package com.travel.vietnamtravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "review")
public class Review extends AbstractAudit{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "description")
    private String description;

//    @Column(name = "created_at")
//    private LocalDateTime createAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
}
