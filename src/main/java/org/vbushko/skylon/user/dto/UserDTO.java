package org.vbushko.skylon.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String description;
    private String image;
    private Integer age;
}
