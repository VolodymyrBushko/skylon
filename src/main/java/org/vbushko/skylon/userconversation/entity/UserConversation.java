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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserConversation {

    @EmbeddedId
    private UserConversationId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    private User user;

    @ManyToOne
    @MapsId("conversationId")
    @JoinColumn(name = "conversation_id")
    @EqualsAndHashCode.Exclude
    private Conversation conversation;

    @CreatedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @LastModifiedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;
}
