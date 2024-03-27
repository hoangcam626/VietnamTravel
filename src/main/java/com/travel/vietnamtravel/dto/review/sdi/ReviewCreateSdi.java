package com.travel.vietnamtravel.dto.review.sdi;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewCreateSdi {

    private Long createBy;

    private Long placeId;

    private int rating;

    private String description;

    private MultipartFile[] image;
}
