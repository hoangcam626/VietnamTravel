package com.travel.vietnamtravel.dto.post.sdi;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor(staticName = "of")
public class PostCreateSdi {

    private Long createdBy;

    private Long placeId;

    private MultipartFile image;

    private String content;

}
