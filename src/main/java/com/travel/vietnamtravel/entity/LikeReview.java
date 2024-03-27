package com.travel.vietnamtravel.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "liked_review")
public class LikeReview extends AbstractAudit{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "liked_by")
    private Long userID;

    @Column(name = "review_id")
    private Long reviewId;

//    @Column(name = "created_at")
//    private LocalDateTime createAt;
}
