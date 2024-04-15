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
@Table(name = "like_place")
public class LikePlace extends AbstractAudit {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "place_id")
    private Long placeId;

}
