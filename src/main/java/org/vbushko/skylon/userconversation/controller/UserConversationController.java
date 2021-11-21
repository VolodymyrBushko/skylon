package org.vbushko.skylon.userconversation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vbushko.skylon.userconversation.dto.UserConversationRequestDTO;
import org.vbushko.skylon.userconversation.dto.UserConversationResponseDTO;
import org.vbushko.skylon.userconversation.service.UserConversationService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserConversationController {

    private final UserConversationService service;

    @PostMapping("/join/conversation")
    @ResponseStatus(HttpStatus.CREATED)
    public UserConversationResponseDTO joinConversation(@RequestBody UserConversationRequestDTO request) {
        return service.joinConversation(request);
    }
}
