package com.travel.vietnamtravel.dto.comment.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor(staticName = "of")
public class CommentCreateSdi {

    private Long createdBy;

    private String content;

    private MultipartFile image;

    private Long postId;

    private Long superCommentId;
}
