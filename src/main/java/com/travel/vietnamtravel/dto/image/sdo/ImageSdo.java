package com.travel.vietnamtravel.dto.image.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.Resource;

@Data
@AllArgsConstructor(staticName = "of")
public class ImageSdo {
    private Resource resource;
    private String mediaType;
}
