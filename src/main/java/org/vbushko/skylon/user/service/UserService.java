package org.vbushko.skylon.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.exception.EntityNotFoundException;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(EntityNotFoundException::new);
    }
}
