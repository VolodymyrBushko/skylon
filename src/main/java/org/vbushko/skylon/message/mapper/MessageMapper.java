package org.vbushko.skylon.message.mapper;

import org.springframework.stereotype.Component;
import org.vbushko.skylon.message.dto.MessageRequestDto;
import org.vbushko.skylon.message.dto.MessageResponseDto;
import org.vbushko.skylon.message.entity.Message;

@Component
public class MessageMapper {

    public Message map(MessageRequestDto request) {
        return Message.builder()
                .content(request.getContent())
                .build();
    }

    public MessageResponseDto map(Message message) {
        return MessageResponseDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .updatedAt(message.getUpdatedAt())
                .build();
    }

    public void merge(MessageRequestDto request, Message message) {
        message.setContent(request.getContent());
    }
}
