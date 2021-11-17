package org.vbushko.skylon.message.mapper;

import org.springframework.stereotype.Component;
import org.vbushko.skylon.message.dto.MessageRequestDTO;
import org.vbushko.skylon.message.dto.MessageResponseDTO;
import org.vbushko.skylon.message.entity.Message;

@Component
public class MessageMapper {

    public Message map(MessageRequestDTO request) {
        return Message.builder()
                .content(request.getContent())
                .build();
    }

    public MessageResponseDTO map(Message message) {
        return MessageResponseDTO.builder()
                .id(message.getId())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .updatedAt(message.getUpdatedAt())
                .build();
    }

    public void merge(MessageRequestDTO request, Message message) {
        message.setContent(request.getContent());
    }
}
