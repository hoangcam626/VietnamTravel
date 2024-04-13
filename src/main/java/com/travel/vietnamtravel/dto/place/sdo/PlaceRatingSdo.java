package com.travel.vietnamtravel.dto.place.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PlaceRatingSdo {
    private Long oneStar;
    private Long twoStar;
    private Long threeStar;
    private Long fourStar;
    private Long fiveStar;

}
