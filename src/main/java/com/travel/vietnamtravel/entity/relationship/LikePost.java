package com.travel.vietnamtravel.entity.relationship;

import com.travel.vietnamtravel.entity.AbstractAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "like_post")
public class LikePost extends AbstractAudit {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

}