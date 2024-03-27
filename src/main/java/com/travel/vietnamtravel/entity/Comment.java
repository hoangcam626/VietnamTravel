package com.travel.vietnamtravel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "comment")
public class Comment extends AbstractAudit{

    @Column(name = "creator_By")
    private Long createdBy;

    @Column(name = "content")
    private String content;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "post_id")
    private Long postID;
}
