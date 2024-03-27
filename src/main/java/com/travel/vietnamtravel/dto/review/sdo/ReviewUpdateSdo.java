package com.travel.vietnamtravel.dto.review.sdo;

import com.travel.vietnamtravel.util.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class ReviewUpdateSdo {

//    private Long createBy;
//
//    private Long placeId;
//
//    private int rating;
//
//    private String description;
//
//    private List<String> urlImages;
//
//    private Timestamp updatedAt;
//
//    private int liked;
//
//    public void setUpdatedAt() {
//        this.updatedAt = DateTimeUtils.resultTimestamp();
//    }
    private Boolean success;
}
