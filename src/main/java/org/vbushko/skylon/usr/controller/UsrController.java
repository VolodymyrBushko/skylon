package org.vbushko.skylon.usr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vbushko.skylon.usr.dto.UsrRequestDTO;
import org.vbushko.skylon.usr.dto.UsrResponseDTO;
import org.vbushko.skylon.usr.service.UsrService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsrController {

    private final UsrService usrService;

    @GetMapping
    public List<UsrResponseDTO> findAll() {
        return usrService.findAll();
    }

    @GetMapping("/{id}")
    public UsrResponseDTO findById(@PathVariable("id") Long id) {
        return usrService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsrResponseDTO save(@RequestBody UsrRequestDTO request) {
        return usrService.save(request);
    }

    @PutMapping("/{id}")
    public UsrResponseDTO update(@PathVariable("id") Long id, @RequestBody UsrRequestDTO request) {
        return usrService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        usrService.deleteById(id);
    }
}
