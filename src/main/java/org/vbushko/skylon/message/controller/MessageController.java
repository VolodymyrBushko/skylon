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

    private final MessageService messageService;

    @GetMapping
    public List<MessageResponseDTO> findAll() {
        return messageService.findAll();
    }

    @GetMapping("/{id}")
    public MessageResponseDTO findById(@PathVariable("id") Long id) {
        return messageService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO save(@RequestBody MessageRequestDTO request) {
        return messageService.save(request);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO update(@PathVariable("id") Long id, @RequestBody MessageRequestDTO request) {
        return messageService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        messageService.deleteById(id);
    }
}
