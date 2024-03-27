package com.travel.vietnamtravel.dto.review.sdi;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewUpdateSdi {

    private Long id;

    private int rating;

    private String description;

    private MultipartFile[] newImages;

    private List<Long> deleteImagesId;
}
