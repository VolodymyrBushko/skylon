package org.vbushko.skylon.userconversation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserConversationId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "conversation_id")
    private Long conversationId;
}
