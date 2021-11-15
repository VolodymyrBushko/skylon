package org.vbushko.skylon.usr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.vbushko.skylon.exception.EntityNotFoundException;
import org.vbushko.skylon.usr.dto.UsrRequestDTO;
import org.vbushko.skylon.usr.dto.UsrResponseDTO;
import org.vbushko.skylon.usr.entity.Usr;
import org.vbushko.skylon.usr.mapper.UsrMapper;
import org.vbushko.skylon.usr.repository.UsrRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsrService {

    private final UsrRepository usrRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UsrResponseDTO> findAll() {
        return usrRepository.findAll()
                .stream()
                .map(UsrMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UsrResponseDTO findById(Long id) {
        Usr usr = usrRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return UsrMapper.map(usr);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UsrResponseDTO save(UsrRequestDTO request) {
        Usr usr = UsrMapper.map(request);
        usrRepository.save(usr);
        return UsrMapper.map(usr);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UsrResponseDTO update(Long id, UsrRequestDTO request) {
        Usr usr = usrRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        UsrMapper.merge(request, usr);
        return UsrMapper.map(usr);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        usrRepository.deleteById(id);
    }
}
