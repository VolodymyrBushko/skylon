package org.vbushko.skylon.security.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vbushko.skylon.security.auth.dto.signin.SignInRequestDto;
import org.vbushko.skylon.security.auth.dto.signin.SignInResponseDto;
import org.vbushko.skylon.security.auth.dto.signup.SignUpRequestDto;
import org.vbushko.skylon.security.auth.dto.signup.SignUpResponseDto;
import org.vbushko.skylon.security.auth.service.AuthService;
import org.vbushko.skylon.security.token.refresh.RefreshTokenRequestDto;
import org.vbushko.skylon.security.token.refresh.RefreshTokenResponseDto;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto request) {
        return service.signUp(request);
    }

    @PostMapping("/sign-in")
    public SignInResponseDto signIn(@RequestBody SignInRequestDto request) {
        return service.signIn(request);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponseDto refreshToken(@RequestBody RefreshTokenRequestDto request) {
        return service.refreshToken(request);
    }
}
