package org.vbushko.skylon.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserResponseDTO extends UserDTO {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
