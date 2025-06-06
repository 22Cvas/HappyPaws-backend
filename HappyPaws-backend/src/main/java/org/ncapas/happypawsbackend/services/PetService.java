package org.ncapas.happypawsbackend.services;

import lombok.NoArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.Pet;
import org.ncapas.happypawsbackend.Domain.dtos.PetRegisterDto;
import org.ncapas.happypawsbackend.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public void createPet(PetRegisterDto register){
        Pet pet = new Pet();
        pet.setName(register.getName());
        pet.setAge(register.getAge());
        pet.setBreed(register.getBreedName());
        pet.setDescription(register.getDescription());
        petRepository.save(pet);
    }

}
