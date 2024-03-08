package com.travel.vietnamtravel.dto.user.sdi;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterSdi {
    private String username;
    private String email;
    private String password;

}
