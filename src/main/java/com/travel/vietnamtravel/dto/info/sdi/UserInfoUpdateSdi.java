package com.travel.vietnamtravel.dto.info.sdi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
public class UserInfoUpdateSdi {
    private Long userId;
    private String username;
    private String fullName;
    private LocalDate birthOfDate;
    private String phoneNumber;
    private String address;
    private int level;
}