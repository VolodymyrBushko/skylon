package org.vbushko.skylon.user.entity;

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
@Table(name = "`user`")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_sequence", allocationSize = 1)
    private Long id;

    @EqualsAndHashCode.Include
    private String firstName;

    @EqualsAndHashCode.Include
    private String lastName;

    @EqualsAndHashCode.Include
    private String login;

    @EqualsAndHashCode.Include
    private String email;

    @EqualsAndHashCode.Include
    private String description;

    @EqualsAndHashCode.Include
    private String image;

    @EqualsAndHashCode.Include
    private String password;

    @EqualsAndHashCode.Include
    private Integer age;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "sender")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserConversation> userConversations = new ArrayList<>();
}
