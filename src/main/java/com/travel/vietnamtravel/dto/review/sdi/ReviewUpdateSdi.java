package com.travel.vietnamtravel.dto.review.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class ReviewUpdateSdi {

    private Long id;

    private int rating;

    private String description;

    private MultipartFile[] newImages;

    private List<Long> deleteImagesId;
}
