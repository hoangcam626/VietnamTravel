package com.travel.vietnamtravel.dto.comment.sdo;

import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class CommentSelfSdo {

    private Long id;

    private UserInfoShortSelfSdo createdBy;

    private String content;

    private Long imageId;

    private Long postID;

    private Long superCommentId;

    private Boolean isLike;

    private Long totalLike;

    private Long totalSubComment;
}
