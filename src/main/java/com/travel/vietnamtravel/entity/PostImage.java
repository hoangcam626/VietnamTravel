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
public class PostImage extends AbstractAudit{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "note")
    private String note;
}
