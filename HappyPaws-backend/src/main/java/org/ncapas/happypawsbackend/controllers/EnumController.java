package org.ncapas.happypawsbackend.controllers;

import org.ncapas.happypawsbackend.Domain.Enums.*;
import org.ncapas.happypawsbackend.Domain.dtos.EnumResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/enums")
public class EnumController {

    @GetMapping("/genders")
    public ResponseEntity<List<EnumResponseDto>> getGenders() {
        List<EnumResponseDto> response = IntStream.range(0, Gender.values().length)
                .mapToObj(i -> new EnumResponseDto(
                        i + 1,
                        Gender.values()[i].getLabel()
                )).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<EnumResponseDto>> getStatuses() {
        List<EnumResponseDto> response = IntStream.range(0, PetStatus.values().length)
                .mapToObj(i -> new EnumResponseDto(
                        i + 1,
                        PetStatus.values()[i].getLabel()
                )).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sizes")
    public ResponseEntity<List<EnumResponseDto>> getSizes() {
        List<EnumResponseDto> response = IntStream.range(0, PetSize.values().length)
                .mapToObj(i -> new EnumResponseDto(
                        i + 1,
                        PetSize.values()[i].getLabel()
                )).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<EnumResponseDto>> getRoles() {
        List<EnumResponseDto> response = IntStream.range(0, UserRol.values().length)
                .mapToObj(i -> new EnumResponseDto(
                        i + 1,
                        UserRol.values()[i].getLabel()
                )).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/application-states")
    public ResponseEntity<List<EnumResponseDto>> getApplicationStates() {
        List<EnumResponseDto> response = IntStream.range(0, ApplicationState.values().length)
                .mapToObj(i -> new EnumResponseDto(
                        i + 1,
                        ApplicationState.values()[i].getLabel()
                )).toList();
        return ResponseEntity.ok(response);
    }
}

