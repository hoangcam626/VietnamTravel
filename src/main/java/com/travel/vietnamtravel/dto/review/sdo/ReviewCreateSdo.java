package com.travel.vietnamtravel.dto.review.sdo;


import com.travel.vietnamtravel.dto.userinfo.sdo.UserInfoShortSelfSdo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class ReviewCreateSdo {

   private Long id;
}
