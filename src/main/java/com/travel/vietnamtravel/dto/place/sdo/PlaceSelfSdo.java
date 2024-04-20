package com.travel.vietnamtravel.dto.place.sdo;

import com.travel.vietnamtravel.dto.administrative.districts.sdo.DistrictShortSelfSdo;
import com.travel.vietnamtravel.dto.administrative.provinces.sdo.ProvinceShortSelfSdo;
import com.travel.vietnamtravel.dto.administrative.wards.sdo.WardShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PlaceSelfSdo {
    private Long id;
    private String name;
    private ProvinceShortSelfSdo province;
    private DistrictShortSelfSdo district;
    private WardShortSelfSdo ward;
    private String description;
    private Long imageId;
    private Boolean isLike;
    private Double rating;
    private Long totalLike;
    private Long totalReview;
    private Long totalVisit;
}
