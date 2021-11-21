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
@Table(name = "usr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String description;
    private String image;
    private String password;
    private Integer age;

    @CreatedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @LastModifiedDate
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "owner")
    @EqualsAndHashCode.Exclude
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    private List<UserConversation> userConversations = new ArrayList<>();
}
