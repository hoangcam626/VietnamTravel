package com.travel.vietnamtravel.entity.relationship;

import com.travel.vietnamtravel.entity.AbstractAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "like_place")
public class LikePlace extends AbstractAudit {

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "place_id")
    private Long placeId;

}
