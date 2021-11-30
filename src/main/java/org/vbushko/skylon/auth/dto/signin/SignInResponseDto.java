package org.vbushko.skylon.auth.dto.signin;

import lombok.*;
import org.vbushko.skylon.security.TokenType;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDto implements Serializable {

    private String accessToken;
    private String refreshToken;
    private TokenType tokenType;
}
