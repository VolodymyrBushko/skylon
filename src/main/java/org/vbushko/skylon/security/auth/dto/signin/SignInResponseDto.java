package org.vbushko.skylon.security.auth.dto.signin;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDto implements Serializable {

    private String accessToken;
    private String refreshToken;
}
