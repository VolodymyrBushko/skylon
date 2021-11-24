package org.vbushko.skylon.auth.dto.signup;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String description;
    private String image;
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
