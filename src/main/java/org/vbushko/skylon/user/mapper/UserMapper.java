package org.vbushko.skylon.user.mapper;

import org.springframework.stereotype.Component;
import org.vbushko.skylon.user.dto.UserRequestDTO;
import org.vbushko.skylon.user.dto.UserResponseDTO;
import org.vbushko.skylon.user.entity.User;

@Component
public class UserMapper {

    public User map(UserRequestDTO request) {
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

    public UserResponseDTO map(User user) {
        return UserResponseDTO.builder()
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

    public void merge(UserRequestDTO request, User user) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setDescription(request.getDescription());
        user.setImage(request.getImage());
        user.setAge(request.getAge());
    }
}
