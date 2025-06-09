package org.ncapas.happypawsbackend.services;

import lombok.RequiredArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.Species;
import org.ncapas.happypawsbackend.Domain.dtos.SpeciesDto;
import org.ncapas.happypawsbackend.Domain.dtos.SpeciesResponseDto;
import org.ncapas.happypawsbackend.repositories.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    public List<SpeciesResponseDto> getAll() {
        return speciesRepository.findAll().stream()
                .map(species -> new SpeciesResponseDto(species.getId_species(), species.getName()))
                .toList();
    }

    public SpeciesResponseDto getById(Integer id) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + id));
        return new SpeciesResponseDto(species.getId_species(), species.getName());
    }

    public SpeciesResponseDto create(SpeciesDto dto) {
        String normalized = normalizeName(dto.getName());

        boolean exists = speciesRepository.findAll().stream()
                .anyMatch(s -> normalizeName(s.getName()).equals(normalized));

        if (exists) {
            throw new RuntimeException("Ya existe una especie con un nombre similar");
        }

        Species species = new Species();
        species.setName(dto.getName().trim());
        Species saved = speciesRepository.save(species);

        return new SpeciesResponseDto(saved.getId_species(), saved.getName());
    }

    public SpeciesResponseDto update(Integer id, SpeciesDto dto) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + id));

        String normalized = normalizeName(dto.getName());

        boolean exists = speciesRepository.findAll().stream()
                .anyMatch(s -> !s.getId_species().equals(id)
                        && normalizeName(s.getName()).equals(normalized));

        if (exists) {
            throw new RuntimeException("Ya existe otra especie con un nombre similar");
        }

        species.setName(dto.getName().trim());
        Species updated = speciesRepository.save(species);

        return new SpeciesResponseDto(updated.getId_species(), updated.getName());
    }

    public String delete(Integer id) {
        if (!speciesRepository.existsById(id)) {
            throw new RuntimeException("No existe la especie con este id: " + id);
        }

        speciesRepository.deleteById(id);
        return "Especie eliminada correctamente";
    }


    private String normalizeName(String name) {
        if (name == null) return null;

        String trimmed = name.trim().toLowerCase();

        if (trimmed.length() > 3 && trimmed.endsWith("s")) {
            trimmed = trimmed.substring(0, trimmed.length() - 1);
        }

        return trimmed;
    }
}
