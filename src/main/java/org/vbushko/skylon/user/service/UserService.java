package org.vbushko.skylon.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.conversation.entity.Conversation;
import org.vbushko.skylon.conversation.repository.ConversationRepository;
import org.vbushko.skylon.conversation.service.ConversationService;
import org.vbushko.skylon.exception.EntityNotFoundException;
import org.vbushko.skylon.user.dto.UserRequestDTO;
import org.vbushko.skylon.user.dto.UserResponseDTO;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.user.mapper.UserMapper;
import org.vbushko.skylon.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ConversationRepository convRepository;
    private final ConversationService convService;

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        return userRepository.findById(id).stream()
                .map(userMapper::map)
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public User findNativeById(Long id) {
        return userRepository.findById(id).stream()
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public UserResponseDTO save(UserRequestDTO request) {
        User user = userMapper.map(request);
        userRepository.save(user);
        return userMapper.map(user);
    }

    @Transactional
    public void joinConversation(Long userId, Long convId) {
    }
}
