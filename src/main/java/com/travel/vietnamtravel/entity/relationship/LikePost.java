package com.travel.vietnamtravel.entity.relationship;

import com.travel.vietnamtravel.entity.AbstractAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "like_post")
public class LikePost extends AbstractAudit {

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "post_id")
    private Long postId;

}