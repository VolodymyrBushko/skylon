package org.vbushko.skylon.conversation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.conversation.dto.ConversationRequestDTO;
import org.vbushko.skylon.conversation.dto.ConversationResponseDTO;
import org.vbushko.skylon.conversation.entity.Conversation;
import org.vbushko.skylon.conversation.mapper.ConversationMapper;
import org.vbushko.skylon.conversation.repository.ConversationRepository;
import org.vbushko.skylon.exception.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationMapper mapper;
    private final ConversationRepository repository;

    @Transactional(readOnly = true)
    public List<ConversationResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConversationResponseDTO findById(Long id) {
        Conversation conversation = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(conversation);
    }

    @Transactional
    public ConversationResponseDTO save(ConversationRequestDTO request) {
        Conversation conversation = mapper.map(request);
        repository.save(conversation);
        return mapper.map(conversation);
    }

    @Transactional
    public ConversationResponseDTO update(Long id, ConversationRequestDTO request) {
        Conversation conversation = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.merge(request, conversation);
        repository.save(conversation);
        return mapper.map(conversation);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
