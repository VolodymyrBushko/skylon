package org.vbushko.skylon.userconversation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserConversationResponseDTO extends UserConversationDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
