package org.vbushko.skylon.message.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDTO implements Serializable {

    private String content;
}
