package com.travel.vietnamtravel.dto.placeimage.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor(staticName = "of")
public class PlaceImageCreateSdi {
    private Long placeId;
    private MultipartFile[] images;
}
