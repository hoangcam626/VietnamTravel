package com.travel.vietnamtravel.dto.place.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceCreateSdi {

    private String name;

    private String provinceCode;

    private String districtCode;

    private String wardCode;

    private String description;

    private MultipartFile image;
}
