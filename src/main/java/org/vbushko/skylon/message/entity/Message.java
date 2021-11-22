package org.vbushko.skylon.message.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.vbushko.skylon.conversation.entity.Conversation;
import org.vbushko.skylon.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String content;

    @CreatedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @LastModifiedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @EqualsAndHashCode.Exclude
    private User sender;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    @EqualsAndHashCode.Exclude
    private Conversation conversation;
}
