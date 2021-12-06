package org.vbushko.skylon.userconversation.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.vbushko.skylon.conversation.entity.Conversation;
import org.vbushko.skylon.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_conversation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserConversation {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private UserConversationId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("conversationId")
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
