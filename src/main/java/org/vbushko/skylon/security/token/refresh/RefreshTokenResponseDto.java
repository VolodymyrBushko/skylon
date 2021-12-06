package org.vbushko.skylon.security.token.refresh;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenResponseDto implements Serializable {

    private String accessToken;
    private String refreshToken;
}
