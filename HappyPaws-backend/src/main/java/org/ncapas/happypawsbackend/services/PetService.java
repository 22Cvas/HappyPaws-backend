package org.ncapas.happypawsbackend.services;

import lombok.NoArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.Pet;
import org.ncapas.happypawsbackend.Domain.Enums.PetStatus;
import org.ncapas.happypawsbackend.Domain.dtos.PetPatchDto;
import org.ncapas.happypawsbackend.Domain.dtos.PetRegisterDto;
import org.ncapas.happypawsbackend.Domain.dtos.PetResponse;
import org.ncapas.happypawsbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ncapas.happypawsbackend.Domain.Entities.Size;
import org.ncapas.happypawsbackend.Domain.Entities.Breed;
import org.ncapas.happypawsbackend.Domain.Entities.Species;
import org.ncapas.happypawsbackend.Domain.Entities.Shelter;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

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

    private PetResponse toPetResponse(Pet pet) {
        return PetResponse.builder()
                .id(pet.getId_pet())
                .name(pet.getName())
                .species(pet.getSpecies() != null ? pet.getSpecies().getName() : null)
                .breed(pet.getBreed() != null ? pet.getBreed().getName() : null)
                .size(pet.getSize() != null && pet.getSize().getName() != null
                        ? pet.getSize().getName().getLabel()
                        : null)
                .gender(pet.getGender() != null ? pet.getGender().name() : null)
                .age(pet.getAge())
                .sterilized(pet.isSterilized())
                .status(pet.getStatus() != null ? pet.getStatus().name() : null)
                .photoUrl(pet.getPhotoURL())
                .entryDate(pet.getEntry_Date() != null
                        ? pet.getEntry_Date().atStartOfDay().toInstant(ZoneOffset.UTC)
                        : null)
                .build();
    }

    public List<PetResponse> getAllPets(String status) {
        List<Pet> pets = (status != null)
                ? petRepository.findByStatus(PetStatus.valueOf(status.toUpperCase()))
                : petRepository.findAll();
        return pets.stream()
                .map(this::toPetResponse)
                .collect(Collectors.toList());
    }


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
        // se guarda como DISPONIBLE al inicio
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

    public PetResponse getPetById(Integer id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        return toPetResponse(pet);
    }

    public void deletePet(Integer id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada");
        }
        petRepository.deleteById(id);
    }

    public PetResponse patchPet(Integer id, PetPatchDto dto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        if (dto.getName() != null) pet.setName(dto.getName());
        if (dto.getAgeValue() != null && dto.getAgeUnit() != null) {
            int edadEnMeses = dto.getAgeUnit().equalsIgnoreCase("AÑOS") ? dto.getAgeValue() * 12 : dto.getAgeValue();
            pet.setAge(edadEnMeses);
        }
        if (dto.getGender() != null) pet.setGender(dto.getGender());
        if (dto.getWeight() != null) pet.setWeight(dto.getWeight());
        if (dto.getSterilized() != null) pet.setSterilized(dto.getSterilized());
        if (dto.getParasiteFree() != null) pet.setParasiteFree(dto.getParasiteFree());
        if (dto.getFullyVaccinated() != null) pet.setFullyVaccinated(dto.getFullyVaccinated());
        if (dto.getEntryDate() != null) pet.setEntry_Date(dto.getEntryDate());
        if (dto.getReviewDate() != null) pet.setReview_Date(dto.getReviewDate());
        if (dto.getDescription() != null) pet.setDescription(dto.getDescription());
        if (dto.getHistory() != null) pet.setHistory(dto.getHistory());
        if (dto.getPhotoURL() != null) pet.setPhotoURL(dto.getPhotoURL());
        if (dto.getStatus() != null) {pet.setStatus(dto.getStatus());}

        if (dto.getBreedId() != null) pet.setBreed(Breed.builder().id_breed(dto.getBreedId()).build());
        if (dto.getSpeciesId() != null) pet.setSpecies(Species.builder().id_species(dto.getSpeciesId()).build());
        if (dto.getSizeId() != null) pet.setSize(Size.builder().id_size(dto.getSizeId()).build());
        if (dto.getShelterId() != null) pet.setShelter(Shelter.builder().id_shelter(dto.getShelterId()).build());

        petRepository.save(pet);
        return toPetResponse(pet);
    }

}