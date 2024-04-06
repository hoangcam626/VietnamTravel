package com.travel.vietnamtravel.dto.comment.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor(staticName = "of")
public class CommentUpdateSdi {
    private Long id;
    private String content;
    private MultipartFile image;

}
