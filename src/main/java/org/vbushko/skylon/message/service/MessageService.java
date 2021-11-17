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
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MessageResponseDTO findById(Long id) {
        Message message = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(message);
    }

    @Transactional
    public MessageResponseDTO save(MessageRequestDTO request) {
        Message message = mapper.map(request);
        repository.save(message);
        return mapper.map(message);
    }

    @Transactional
    public MessageResponseDTO update(Long id, MessageRequestDTO request) {
        Message message = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.merge(request, message);
        repository.save(message);
        return mapper.map(message);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
