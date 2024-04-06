package com.travel.vietnamtravel.dto.place.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceSelfSdo {
    private String name;

    private String provinceCode;

    private String districtCode;

    private String wardCode;

    private String description;

//    private Set<Image> images = new HashSet<>();

    private Boolean isLike;

    private Double rating;

    private Long totalLike;

    private Long totalReview;
}
