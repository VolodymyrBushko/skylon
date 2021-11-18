package org.vbushko.skylon.conversation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vbushko.skylon.conversation.dto.ConversationRequestDTO;
import org.vbushko.skylon.conversation.dto.ConversationResponseDTO;
import org.vbushko.skylon.conversation.service.ConversationService;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private ConversationService service;

    @GetMapping
    public List<ConversationResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ConversationResponseDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConversationResponseDTO save(@RequestBody ConversationRequestDTO request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public ConversationResponseDTO update(@PathVariable("id") Long id, @RequestBody ConversationRequestDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
