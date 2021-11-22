package org.vbushko.skylon.userconversation.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserConversationRequestDTO implements Serializable {

    private Long userId;
    private Long conversationId;
}
