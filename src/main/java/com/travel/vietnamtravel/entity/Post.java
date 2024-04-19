package com.travel.vietnamtravel.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table
@Entity
public class Post extends AbstractAudit{

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "content")
    private String content;
}
