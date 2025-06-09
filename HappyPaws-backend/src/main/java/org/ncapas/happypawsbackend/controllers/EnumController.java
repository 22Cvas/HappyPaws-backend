package org.ncapas.happypawsbackend.controllers;

import org.ncapas.happypawsbackend.Domain.Enums.Gender;
import org.ncapas.happypawsbackend.Domain.Enums.PetStatus;
import org.ncapas.happypawsbackend.Domain.Enums.PetSize;
import org.ncapas.happypawsbackend.Domain.dtos.EnumResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enums")
public class EnumController {

    @GetMapping("/genders")
    public ResponseEntity<List<EnumResponseDto>> getGenders() {
        List<EnumResponseDto> response = Arrays.stream(Gender.values())
                .map(g -> new EnumResponseDto(g.name(), g.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<EnumResponseDto>> getStatuses() {
        List<EnumResponseDto> response = Arrays.stream(PetStatus.values())
                .map(s -> new EnumResponseDto(s.name(), s.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sizes")
    public ResponseEntity<List<EnumResponseDto>> getSizes() {
        List<EnumResponseDto> response = Arrays.stream(PetSize.values())
                .map(size -> new EnumResponseDto(size.name(), size.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
