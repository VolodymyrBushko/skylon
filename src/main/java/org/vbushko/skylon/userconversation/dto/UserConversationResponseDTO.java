package org.vbushko.skylon.userconversation.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserConversationResponseDTO implements Serializable {

    private Long userId;
    private Long conversationId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
