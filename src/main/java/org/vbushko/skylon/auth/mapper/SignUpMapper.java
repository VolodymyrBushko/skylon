package org.vbushko.skylon.auth.mapper;

import org.springframework.stereotype.Component;
import org.vbushko.skylon.auth.dto.signup.SignUpRequestDto;
import org.vbushko.skylon.auth.dto.signup.SignUpResponseDto;
import org.vbushko.skylon.user.entity.User;

@Component
public class SignUpMapper {

    public User map(SignUpRequestDto request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .login(request.getLogin())
                .password(request.getPassword())
                .email(request.getEmail())
                .description(request.getDescription())
                .image(request.getImage())
                .age(request.getAge())
                .build();
    }

    public SignUpResponseDto map(User user) {
        return SignUpResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .email(user.getEmail())
                .description(user.getDescription())
                .image(user.getImage())
                .age(user.getAge())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
