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
public class PlaceShortSelfSdo {
    private Long id;
    private String name;
    private ProvinceShortSelfSdo province;
    private DistrictShortSelfSdo district;
    private WardShortSelfSdo ward;
    private Long imageId;
}
