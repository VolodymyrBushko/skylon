package org.vbushko.skylon.conversation.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.vbushko.skylon.message.entity.Message;
import org.vbushko.skylon.userconversation.entity.UserConversation;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conversation_id_generator")
    @SequenceGenerator(name = "conversation_id_generator", sequenceName = "conversation_id_sequence", allocationSize = 1)
    private Long id;

    @EqualsAndHashCode.Include
    private String title;

    @EqualsAndHashCode.Include
    private String description;

    @EqualsAndHashCode.Include
    private String image;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "conversation")
    private List<UserConversation> userConversations = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "conversation"
    )
    private List<Message> messages = new ArrayList<>();
}
