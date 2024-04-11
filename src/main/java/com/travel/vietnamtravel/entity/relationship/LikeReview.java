package com.travel.vietnamtravel.entity.relationship;

import com.travel.vietnamtravel.entity.AbstractAudit;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "like_review")
public class LikeReview extends AbstractAudit {

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "review_id")
    private Long reviewId;

}
