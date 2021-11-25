package org.vbushko.skylon.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.auth.dto.signin.SignInRequestDto;
import org.vbushko.skylon.auth.dto.signin.SignInResponseDto;
import org.vbushko.skylon.auth.dto.signup.SignUpRequestDto;
import org.vbushko.skylon.auth.dto.signup.SignUpResponseDto;
import org.vbushko.skylon.auth.mapper.SignUpMapper;
import org.vbushko.skylon.exception.EntityAlreadyExistsException;
import org.vbushko.skylon.exception.EntityNotFoundException;
import org.vbushko.skylon.security.JwtProvider;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SignUpMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto request) {
        User user = mapper.map(request);
        boolean exists = repository.existsByLoginAndEmail(user.getLogin(), user.getEmail());
        if (exists) {
            throw new EntityAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return mapper.map(user);
    }

    @Transactional(readOnly = true)
    public SignInResponseDto signIn(SignInRequestDto request) {
        repository.findByLogin(request.getLogin())
                .filter(e -> passwordEncoder.matches(request.getPassword(), e.getPassword()))
                .orElseThrow(EntityNotFoundException::new);

        return SignInResponseDto.builder()
                .token(jwtProvider.generateToken(request.getLogin()))
                .build();
    }
}