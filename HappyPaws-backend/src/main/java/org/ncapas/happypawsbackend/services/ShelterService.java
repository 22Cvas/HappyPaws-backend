package org.ncapas.happypawsbackend.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.Shelter;
import org.ncapas.happypawsbackend.Domain.dtos.ShelterDto;
import org.ncapas.happypawsbackend.Domain.dtos.ShelterResponseDto;
import org.ncapas.happypawsbackend.repositories.ShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;

    public List<ShelterResponseDto> getAllShelters() {
        return shelterRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public ShelterResponseDto getShelterById(Integer id) {
        Shelter shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refugio no encontrado"));
        return toResponseDto(shelter);
    }


    @Transactional
    public Shelter createShelter(ShelterDto dto) {
        if (shelterRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Nombre del shelter ya existe");
        }

        Shelter shelter = new Shelter();
        shelter.setName(dto.getName());
        shelter.setAddress(dto.getAddress());
        shelter.setPhone(Integer.parseInt(dto.getPhone()));
        shelter.setEmail(dto.getEmail());

        return shelterRepository.save(shelter);
    }

    private ShelterResponseDto toResponseDto(Shelter shelter) {
        ShelterResponseDto dto = new ShelterResponseDto();
        dto.setId(shelter.getId_shelter());
        dto.setName(shelter.getName());
        dto.setAddress(shelter.getAddress());
        dto.setPhone(String.valueOf(shelter.getPhone()));
        dto.setEmail(shelter.getEmail());
        return dto;
    }

    @Transactional
    public ShelterResponseDto updateShelter(Integer id, ShelterDto dto) {
        Shelter shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refugio no encontrado"));

        shelter.setName(dto.getName());
        shelter.setAddress(dto.getAddress());
        shelter.setPhone(Integer.parseInt(dto.getPhone()));
        shelter.setEmail(dto.getEmail());

        Shelter updated = shelterRepository.save(shelter);

        ShelterResponseDto response = new ShelterResponseDto();
        response.setId(updated.getId_shelter());
        response.setName(updated.getName());
        response.setAddress(updated.getAddress());
        response.setPhone(String.valueOf(updated.getPhone()));
        response.setEmail(updated.getEmail());

        return toResponseDto(updated);
    }

    @Transactional
    public void deleteShelter(Integer id) {
        if (!shelterRepository.existsById(id)) {
            throw new RuntimeException("Refugio no encontrado");
        }
        shelterRepository.deleteById(id);
    }

}

