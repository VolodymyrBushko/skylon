package org.vbushko.skylon.security.token.refresh;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.exception.RefreshTokenException;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.util.TimeUtil;

import java.util.UUID;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${application.security.jwt.refresh-token-expiration-sec}")
    private int jwtTokenExpirationSec;

    private final RefreshTokenRepository repository;
    private final TimeUtil timeUtil;

    @Transactional
    public RefreshToken save(RefreshToken token) {
        return repository.save(token);
    }

    @Transactional(readOnly = true)
    public void validate(String token) {
        repository.findByToken(token)
                .filter(e -> e.getExpiredAt().compareTo(now()) > 0)
                .orElseThrow(RefreshTokenException::new);
    }

    @Transactional
    public void deleteByUserLogin(String login) {
        repository.deleteByUserLogin(login);
        repository.flush();
    }

    public RefreshToken generate(User user) {
        return RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiredAt(timeUtil.getFuture(jwtTokenExpirationSec))
                .user(user)
                .build();
    }
}
