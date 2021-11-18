package org.vbushko.skylon.conversation.mapper;

import org.springframework.stereotype.Component;
import org.vbushko.skylon.conversation.dto.ConversationRequestDTO;
import org.vbushko.skylon.conversation.dto.ConversationResponseDTO;
import org.vbushko.skylon.conversation.entity.Conversation;

@Component
public class ConversationMapper {

    public Conversation map(ConversationRequestDTO request) {
        return Conversation.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .image(request.getImage())
                .build();
    }

    public ConversationResponseDTO map(Conversation conversation) {
        return ConversationResponseDTO.builder()
                .id(conversation.getId())
                .title(conversation.getTitle())
                .description(conversation.getDescription())
                .image(conversation.getImage())
                .createdAt(conversation.getCreatedAt())
                .updatedAt(conversation.getUpdatedAt())
                .build();
    }

    public void merge(ConversationRequestDTO request, Conversation conversation) {
        conversation.setTitle(request.getTitle());
        conversation.setDescription(request.getDescription());
        conversation.setImage(request.getImage());
    }
}
