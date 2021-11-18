package org.vbushko.skylon.conversation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ConversationResponseDTO extends ConversationDTO {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
