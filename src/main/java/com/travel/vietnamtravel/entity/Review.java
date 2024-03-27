package com.travel.vietnamtravel.entity;

import com.travel.vietnamtravel.entity.media.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "createBy")
    private Long userId;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "description")
    private String description;

    @OneToMany
    private List<Image> images = new ArrayList<>();

//    @Column(name = "created_at")
//    private LocalDateTime createAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
}
