package org.vbushko.skylon.userconversation.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserConversationId implements Serializable {

    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long userId;

    @Column(name = "conversation_id")
    @EqualsAndHashCode.Include
    private Long conversationId;
}
