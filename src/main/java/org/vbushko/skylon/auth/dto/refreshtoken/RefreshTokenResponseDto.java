package org.vbushko.skylon.auth.dto.refreshtoken;

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
