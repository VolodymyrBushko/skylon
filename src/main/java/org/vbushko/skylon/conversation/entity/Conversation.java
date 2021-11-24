package org.vbushko.skylon.conversation.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.vbushko.skylon.message.entity.Message;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.userconversation.entity.UserConversation;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conversation_id_generator")
    @SequenceGenerator(name = "conversation_id_generator", sequenceName = "conversation_id_sequence", allocationSize = 1)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String title;
    private String description;
    private String image;

    @CreatedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @LastModifiedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @EqualsAndHashCode.Exclude
    private User owner;

    @OneToMany(mappedBy = "conversation")
    @EqualsAndHashCode.Exclude
    private List<UserConversation> userConversations = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "conversation"
    )
    @EqualsAndHashCode.Exclude
    private List<Message> messages = new ArrayList<>();
}
