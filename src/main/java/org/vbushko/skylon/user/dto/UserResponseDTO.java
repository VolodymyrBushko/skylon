package org.vbushko.skylon.user.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO implements Serializable {

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
