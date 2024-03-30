package com.travel.vietnamtravel.entity.relationship;

import com.travel.vietnamtravel.entity.AbstractAudit;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "place_image")
public class PlaceImage extends AbstractAudit {

    @Column(name = "place_id")
    private Long placeId;
    @Column(name = "image_id")
    private Long imageId;
}
