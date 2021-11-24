package org.vbushko.skylon.auth.dto.signin;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDto implements Serializable {

    private String token;
}
