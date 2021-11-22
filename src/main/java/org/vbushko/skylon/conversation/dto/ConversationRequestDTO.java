package org.vbushko.skylon.conversation.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationRequestDTO implements Serializable {

    private String title;
    private String description;
    private String image;
}
