package org.vbushko.skylon.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private final UserMapper mapper;
    private final UserRepository repository;

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.map(user);
    }

    @Transactional
    public UserResponseDTO save(UserRequestDTO request) {
        User user = mapper.map(request);
        repository.save(user);
        return mapper.map(user);
    }

    @Transactional
    public UserResponseDTO update(Long id, UserRequestDTO request) {
        User user = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        mapper.merge(request, user);
        repository.save(user);
        return mapper.map(user);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
