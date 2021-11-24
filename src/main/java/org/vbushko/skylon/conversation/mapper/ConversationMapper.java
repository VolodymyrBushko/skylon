package org.vbushko.skylon.conversation.mapper;

import org.springframework.stereotype.Component;
import org.vbushko.skylon.conversation.dto.ConversationRequestDto;
import org.vbushko.skylon.conversation.dto.ConversationResponseDto;
import org.vbushko.skylon.conversation.entity.Conversation;

@Component
public class ConversationMapper {

    public Conversation map(ConversationRequestDto request) {
        return Conversation.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .image(request.getImage())
                .build();
    }

    public ConversationResponseDto map(Conversation conversation) {
        return ConversationResponseDto.builder()
                .id(conversation.getId())
                .title(conversation.getTitle())
                .description(conversation.getDescription())
                .image(conversation.getImage())
                .createdAt(conversation.getCreatedAt())
                .updatedAt(conversation.getUpdatedAt())
                .build();
    }

    public void merge(ConversationRequestDto request, Conversation conversation) {
        conversation.setTitle(request.getTitle());
        conversation.setDescription(request.getDescription());
        conversation.setImage(request.getImage());
    }
}
