package com.travel.vietnamtravel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

@Entity
@Table(name = "sub_comment")
public class SubComment extends AbstractAudit{

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "description")
    private String description;
}
