package org.vbushko.skylon.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.exception.EntityNotFoundException;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public User findByLoginAndPassword(String login, String password) {
        return repository.findByLogin(login)
                .filter(e -> passwordEncoder.matches(password, e.getPassword()))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public User findByRefreshToken(String token) {
        return repository.findByRefreshToken(token)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean existsByLoginOrEmail(String login, String email) {
        return repository.existsByLoginOrEmail(login, email);
    }
}
