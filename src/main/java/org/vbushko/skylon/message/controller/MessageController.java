package org.vbushko.skylon.message.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vbushko.skylon.message.dto.MessageRequestDTO;
import org.vbushko.skylon.message.dto.MessageResponseDTO;
import org.vbushko.skylon.message.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @GetMapping
    public List<MessageResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MessageResponseDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO save(@RequestBody MessageRequestDTO request) {
        return service.save(request);
    }
}
