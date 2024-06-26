package com.travel.vietnamtravel.dto.post.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PostUpdateSdi {

    private Long id;

    private Long placeId;

    private String content;
}
