package org.vbushko.skylon.auth.dto.refreshtoken;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequestDto implements Serializable {

    private String refreshToken;
}
