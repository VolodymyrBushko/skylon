package org.vbushko.skylon.auth.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.auth.dto.refreshtoken.RefreshTokenRequestDto;
import org.vbushko.skylon.auth.dto.refreshtoken.RefreshTokenResponseDto;
import org.vbushko.skylon.auth.dto.signin.SignInRequestDto;
import org.vbushko.skylon.auth.dto.signin.SignInResponseDto;
import org.vbushko.skylon.auth.dto.signup.SignUpRequestDto;
import org.vbushko.skylon.auth.dto.signup.SignUpResponseDto;
import org.vbushko.skylon.auth.mapper.SignUpMapper;
import org.vbushko.skylon.exception.EntityAlreadyExistsException;
import org.vbushko.skylon.security.token.access.AccessTokenService;
import org.vbushko.skylon.security.token.access.AccessTokenType;
import org.vbushko.skylon.security.token.refresh.RefreshToken;
import org.vbushko.skylon.security.token.refresh.RefreshTokenService;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.user.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final AccessTokenType TOKEN_TYPE = AccessTokenType.BEARER;

    private final SignUpMapper signInMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto request) {
        User user = Optional.of(request)
                .map(signInMapper::map)
                .filter(usr -> !userService.existsByLoginOrEmail(usr.getLogin(), usr.getEmail()))
                .orElseThrow(EntityAlreadyExistsException::new);

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        userService.save(user);
        return signInMapper.map(user);
    }

    @Transactional
    public SignInResponseDto signIn(SignInRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());

        ImmutablePair<String, String> tokens = getTokensPair(user);
        return new SignInResponseDto(tokens.getLeft(), tokens.getRight());
    }

    @Transactional
    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto request) {
        String token = request.getRefreshToken();
        refreshTokenService.validate(token);
        User user = userService.findByRefreshToken(token);

        ImmutablePair<String, String> tokens = getTokensPair(user);
        return new RefreshTokenResponseDto(tokens.getLeft(), tokens.getRight());
    }

    private ImmutablePair<String, String> getTokensPair(User user) {
        String login = user.getLogin();
        String accessToken = String.format("%s %s", TOKEN_TYPE.getType(), accessTokenService.generate(login));
        RefreshToken refreshToken = refreshTokenService.generate(user);

        refreshTokenService.deleteByUserLogin(login);
        refreshTokenService.save(refreshToken);

        return new ImmutablePair<>(accessToken, refreshToken.getToken());
    }
}
