package org.vbushko.skylon.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vbushko.skylon.user.dto.UserRequestDTO;
import org.vbushko.skylon.user.dto.UserResponseDTO;
import org.vbushko.skylon.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO save(@RequestBody UserRequestDTO request) {
        return service.save(request);
    }

    @GetMapping("/{userId}/join/conversations/{convId}")
    public void joinConversation(@PathVariable("userId") Long userId, @PathVariable("convId") Long convId) {
        service.joinConversation(userId, convId);
    }
}
