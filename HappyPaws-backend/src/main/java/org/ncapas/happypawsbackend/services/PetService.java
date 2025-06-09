package org.ncapas.happypawsbackend.services;

import lombok.NoArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.Pet;
import org.ncapas.happypawsbackend.Domain.Enums.PetStatus;
import org.ncapas.happypawsbackend.Domain.dtos.PetRegisterDto;
import org.ncapas.happypawsbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@NoArgsConstructor
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private SizeRepository sizeRepository;

    public void createPet(PetRegisterDto register) {
        Pet pet = new Pet();
        pet.setName(register.getName());

        // convertir edad a meses
        int edadEnMeses = register.getAgeUnit().equalsIgnoreCase("años")
                ? register.getAgeValue() * 12
                : register.getAgeValue();
        pet.setAge(edadEnMeses);

        pet.setGender(register.getGender());
        pet.setWeight(register.getWeight());
        pet.setSterilized(register.isSterilized());
        pet.setParasiteFree(register.isParasiteFree());
        pet.setFullyVaccinated(register.isFullyVaccinated());
        pet.setEntry_Date(register.getEntryDate());
        pet.setReview_Date(register.getReviewDate());
        pet.setDescription(register.getDescription());
        pet.setHistory(register.getHistory());
        pet.setPhotoURL(register.getPhotoURL());
        // siempre se guarda como DISPONIBLE al inicio
        pet.setStatus(PetStatus.DISPONIBLE);

        pet.setShelter(shelterRepository.findById(register.getShelterId())
                .orElseThrow(() -> new RuntimeException("Shelter no encontrado")));
        pet.setSpecies(speciesRepository.findById(register.getSpeciesId())
                .orElseThrow(() -> new RuntimeException("Especie no encontrada")));
        pet.setBreed(breedRepository.findById(register.getBreedId())
                .orElseThrow(() -> new RuntimeException("Raza no encontrada")));
        pet.setSize(sizeRepository.findById(register.getSizeId())
                .orElseThrow(() -> new RuntimeException("Tamaño no encontrado")));

        petRepository.save(pet);
    }
}