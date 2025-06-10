package org.ncapas.happypawsbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ncapas.happypawsbackend.Domain.dtos.PetPatchDto;
import org.ncapas.happypawsbackend.Domain.dtos.PetRegisterDto;
import org.ncapas.happypawsbackend.Domain.dtos.PetResponse;
import org.ncapas.happypawsbackend.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/all")
    public ResponseEntity<List<PetResponse>> getAllPets(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(petService.getAllPets(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable UUID id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable UUID id) {
        petService.deletePet(id);
        return ResponseEntity.ok("Mascota eliminada correctamente");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PetResponse> patchPet(@PathVariable UUID id, @RequestBody PetPatchDto dto) {
        PetResponse updated = petService.patchPet(id, dto);
        return ResponseEntity.ok(updated);
    }

}