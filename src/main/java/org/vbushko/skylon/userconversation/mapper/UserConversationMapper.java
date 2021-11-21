package org.vbushko.skylon.userconversation.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.vbushko.skylon.conversation.entity.Conversation;
import org.vbushko.skylon.conversation.service.ConversationService;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.user.service.UserService;
import org.vbushko.skylon.userconversation.dto.UserConversationRequestDTO;
import org.vbushko.skylon.userconversation.dto.UserConversationResponseDTO;
import org.vbushko.skylon.userconversation.entity.UserConversation;

@Component
@RequiredArgsConstructor
public class UserConversationMapper {

    private final UserService userService;
    private final ConversationService convService;

    public UserConversation map(UserConversationRequestDTO request) {
        User user = userService.findNativeById(request.getUserId());
        Conversation conv = convService.findNativeById(request.getConversationId());

        return UserConversation.builder()
                .user(user)
                .conversation(conv)
                .build();
    }

    public UserConversationResponseDTO map(UserConversation userConv) {
        return UserConversationResponseDTO.builder()
                .userId(userConv.getId().getUserId())
                .conversationId(userConv.getId().getConversationId())
                .createdAt(userConv.getCreatedAt())
                .updatedAt(userConv.getUpdatedAt())
                .build();
    }
}
