package org.vbushko.skylon.userconversation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.userconversation.dto.UserConversationRequestDTO;
import org.vbushko.skylon.userconversation.dto.UserConversationResponseDTO;
import org.vbushko.skylon.userconversation.entity.UserConversation;
import org.vbushko.skylon.userconversation.mapper.UserConversationMapper;
import org.vbushko.skylon.userconversation.repository.UserConversationRepository;

@Service
@RequiredArgsConstructor
public class UserConversationService {

    private final UserConversationMapper mapper;
    private final UserConversationRepository repository;

    @Transactional
    public UserConversationResponseDTO joinConversation(UserConversationRequestDTO request) {
        UserConversation userConv = mapper.map(request);
        repository.save(userConv);
        return mapper.map(userConv);
    }
}
