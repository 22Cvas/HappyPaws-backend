package org.ncapas.happypawsbackend.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ncapas.happypawsbackend.Domain.dtos.BreedDto;
import org.ncapas.happypawsbackend.Domain.dtos.BreedResponseDto;
import org.ncapas.happypawsbackend.services.BreedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breeds")
@RequiredArgsConstructor
public class BreedController {

    private final BreedService breedService;

    @GetMapping("/all")
    public ResponseEntity<List<BreedResponseDto>> getAll() {
        return ResponseEntity.ok(breedService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreedResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(breedService.getById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<BreedResponseDto> create(@Valid @RequestBody BreedDto dto) {
        return ResponseEntity.ok(breedService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BreedResponseDto> update(@PathVariable Integer id, @Valid @RequestBody BreedDto dto) {
        return ResponseEntity.ok(breedService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        String msg = breedService.delete(id);
        return ResponseEntity.ok(msg);
    }
}

