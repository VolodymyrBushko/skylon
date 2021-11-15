package org.vbushko.skylon.usr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UsrRequestDTO extends UsrDTO {

    private String password;
}
