package org.ncapas.happypawsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.ncapas.happypawsbackend.Domain.dtos.PetAttributeRequestDto;
import org.ncapas.happypawsbackend.Domain.dtos.PetAttributeResponseDto;
import org.ncapas.happypawsbackend.services.PetAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pet_attributes")
@RequiredArgsConstructor
public class PetAttributeController {

    @Autowired
    private PetAttributeService petattributeservice;

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody PetAttributeRequestDto dto) {
        petattributeservice.create(dto);
        return ResponseEntity.ok("Atributo creado con éxito");
    }

    @GetMapping("/bypet/{petId}")
    public ResponseEntity<List<PetAttributeResponseDto>> getByPet(@PathVariable UUID petId) {
        return ResponseEntity.ok(petattributeservice.getByPet(petId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PetAttributeRequestDto dto) {
        petattributeservice.update(id, dto);
        return ResponseEntity.ok("Atributo actualizado con éxito");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        petattributeservice.delete(id);
        return ResponseEntity.ok("Atributo eliminado con éxito");
    }
}
