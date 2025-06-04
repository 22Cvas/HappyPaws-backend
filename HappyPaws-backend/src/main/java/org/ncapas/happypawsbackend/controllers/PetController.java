package org.ncapas.happypawsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.ncapas.happypawsbackend.Domain.dtos.PetDto;
import org.ncapas.happypawsbackend.services.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

}