package org.vbushko.skylon.security.token.refresh;

import lombok.*;
import org.vbushko.skylon.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refresh_token_id_generator")
    @SequenceGenerator(name = "refresh_token_id_generator", sequenceName = "refresh_token_id_sequence", allocationSize = 1)
    private Long id;

    @EqualsAndHashCode.Include
    private String token;

    @EqualsAndHashCode.Include
    private LocalDateTime expiredAt;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
