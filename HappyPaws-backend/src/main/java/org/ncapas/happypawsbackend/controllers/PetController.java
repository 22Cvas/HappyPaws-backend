package org.ncapas.happypawsbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ncapas.happypawsbackend.Domain.dtos.PetRegisterDto;
import org.ncapas.happypawsbackend.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/register")
    public ResponseEntity<?> registerPet(@Valid @RequestBody PetRegisterDto dto) {
        petService.createPet(dto);
        return ResponseEntity.ok().body("Mascota registrada exitosamente");
    }

}