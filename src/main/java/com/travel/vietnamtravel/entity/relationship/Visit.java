package com.travel.vietnamtravel.entity.relationship;

import com.travel.vietnamtravel.entity.AbstractAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "visit")
public class Visit extends AbstractAudit {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "place_Id")
    private Long placeId;
    @Column(name = "time")
    private LocalDateTime time;
}
