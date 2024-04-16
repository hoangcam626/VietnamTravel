package com.travel.vietnamtravel.dto.user.sdi;

import lombok.*;

@Data
@AllArgsConstructor(staticName = "of")
public class UserRegisterSdi {
    private String username;
    private String email;
    private String password;

}
