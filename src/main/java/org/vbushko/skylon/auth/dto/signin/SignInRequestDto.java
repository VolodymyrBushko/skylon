package org.vbushko.skylon.auth.dto.signin;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequestDto implements Serializable {

    private String login;
    private String password;
}
