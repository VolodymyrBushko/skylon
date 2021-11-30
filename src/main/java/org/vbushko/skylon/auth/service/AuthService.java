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
import org.vbushko.skylon.security.TokenType;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.user.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SignUpMapper mapper;
    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto request) {
        User user = Optional.ofNullable(request)
                .map(mapper::map)
                .filter(e -> !service.existsByLoginOrEmail(e.getLogin(), e.getEmail()))
                .orElseThrow(EntityAlreadyExistsException::new);

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        service.save(user);
        return mapper.map(user);
    }

    @Transactional(readOnly = true)
    public SignInResponseDto signIn(SignInRequestDto request) {
        User user = service.findByLogin(request.getLogin());
        boolean passMatched = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (passMatched) {
            String accessToken = jwtProvider.generateAccessToken(user.getLogin());
            String refreshToken = jwtProvider.generateRefreshToken(user.getLogin());

            return SignInResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .tokenType(TokenType.BEARER)
                    .build();
        }

        throw new EntityNotFoundException();
    }
}
