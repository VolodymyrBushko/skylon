package org.vbushko.skylon.auth.dto.signup;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto implements Serializable {

    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String description;
    private String image;
    private String password;
    private Integer age;
}
