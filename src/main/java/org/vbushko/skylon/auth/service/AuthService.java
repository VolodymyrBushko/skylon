package org.vbushko.skylon.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.security.token.refresh.RefreshTokenRequestDto;
import org.vbushko.skylon.security.token.refresh.RefreshTokenResponseDto;
import org.vbushko.skylon.auth.dto.signin.SignInRequestDto;
import org.vbushko.skylon.auth.dto.signin.SignInResponseDto;
import org.vbushko.skylon.auth.dto.signup.SignUpRequestDto;
import org.vbushko.skylon.auth.dto.signup.SignUpResponseDto;
import org.vbushko.skylon.auth.mapper.SignUpMapper;
import org.vbushko.skylon.exception.EntityAlreadyExistsException;
import org.vbushko.skylon.exception.EntityNotFoundException;
import org.vbushko.skylon.security.token.access.AccessTokenService;
import org.vbushko.skylon.security.token.access.AccessTokenType;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.user.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final AccessTokenType TOKEN_TYPE = AccessTokenType.BEARER;

    private final SignUpMapper mapper;
    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;

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
            String accessToken = String.format("%s %s", TOKEN_TYPE.getType(), accessTokenService.generate(user.getLogin()));
            return SignInResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken("") // TODO: need a refreshToken
                    .build();
        }

        throw new EntityNotFoundException();
    }

    @Transactional(readOnly = true)
    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto request) {
        return null;
    }
}
