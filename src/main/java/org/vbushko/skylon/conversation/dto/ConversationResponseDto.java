package org.vbushko.skylon.conversation.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponseDto implements Serializable {

    private Long id;
    private String title;
    private String description;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
