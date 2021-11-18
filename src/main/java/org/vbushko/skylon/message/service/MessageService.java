package org.vbushko.skylon.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.exception.EntityNotFoundException;
import org.vbushko.skylon.message.dto.MessageRequestDTO;
import org.vbushko.skylon.message.dto.MessageResponseDTO;
import org.vbushko.skylon.message.entity.Message;
import org.vbushko.skylon.message.mapper.MessageMapper;
import org.vbushko.skylon.message.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageMapper mapper;
    private final MessageRepository repository;

    @Transactional(readOnly = true)
    public List<MessageResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MessageResponseDTO findById(Long id) {
        return repository.findById(id).stream()
                .map(mapper::map)
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public MessageResponseDTO save(MessageRequestDTO request) {
        Message message = mapper.map(request);
        repository.save(message);
        return mapper.map(message);
    }
}
