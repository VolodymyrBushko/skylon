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

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    @Transactional(readOnly = true)
    public List<MessageResponseDTO> findAll() {
        return messageRepository.findAll()
                .stream()
                .map(messageMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MessageResponseDTO findById(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return messageMapper.map(message);
    }

    @Transactional
    public MessageResponseDTO save(MessageRequestDTO request) {
        Message message = messageMapper.map(request);
        messageRepository.save(message);
        return messageMapper.map(message);
    }

    @Transactional
    public MessageResponseDTO update(Long id, MessageRequestDTO request) {
        Message message = messageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        messageMapper.merge(request, message);
        messageRepository.save(message);
        return messageMapper.map(message);
    }

    @Transactional
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }
}
