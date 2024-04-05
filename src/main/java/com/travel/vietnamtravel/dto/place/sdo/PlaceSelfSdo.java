package com.travel.vietnamtravel.dto.place.sdo;

import com.travel.vietnamtravel.entity.media.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceSelfSdo {
    private String name;

    private String provinceCode;

    private String districtCode;

    private String wardCode;

    private String description;

//    private Set<Image> images = new HashSet<>();
    private int countLike;
    private Boolean isLike;
}
