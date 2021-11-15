package org.vbushko.skylon.usr.mapper;

import lombok.experimental.UtilityClass;
import org.vbushko.skylon.usr.dto.UsrRequestDTO;
import org.vbushko.skylon.usr.dto.UsrResponseDTO;
import org.vbushko.skylon.usr.entity.Usr;

@UtilityClass
public class UsrMapper {

    public static Usr map(UsrRequestDTO request) {
        return Usr.builder()
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

    public static UsrResponseDTO map(Usr usr) {
        return UsrResponseDTO.builder()
                .id(usr.getId())
                .firstName(usr.getFirstName())
                .lastName(usr.getLastName())
                .login(usr.getLogin())
                .email(usr.getEmail())
                .description(usr.getDescription())
                .image(usr.getImage())
                .age(usr.getAge())
                .createdAt(usr.getCreatedAt())
                .updatedAt(usr.getUpdatedAt())
                .build();
    }

    public static void merge(UsrRequestDTO request, Usr usr) {
        usr.setFirstName(request.getFirstName());
        usr.setLastName(request.getLastName());
        usr.setLogin(request.getLogin());
        usr.setPassword(request.getPassword());
        usr.setEmail(request.getEmail());
        usr.setDescription(request.getDescription());
        usr.setImage(request.getImage());
        usr.setAge(request.getAge());
    }
}
