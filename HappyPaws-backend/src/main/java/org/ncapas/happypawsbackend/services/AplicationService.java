package org.ncapas.happypawsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.ncapas.happypawsbackend.Domain.Entities.Aplication;
import org.ncapas.happypawsbackend.Domain.Entities.Pet;
import org.ncapas.happypawsbackend.Domain.Entities.User;
import org.ncapas.happypawsbackend.Domain.Enums.ApplicationState;
import org.ncapas.happypawsbackend.Domain.dtos.AplicationRegisterDto;
import org.ncapas.happypawsbackend.Domain.dtos.AplicationUpdateDto;
import org.ncapas.happypawsbackend.repositories.AplicationRepository;
import org.ncapas.happypawsbackend.repositories.PetRepository;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AplicationService {

    @Autowired
    private AplicationRepository aplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    public void createAplication(AplicationRegisterDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getUserId()));

        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + request.getPetId()));
        Aplication aplication = new Aplication();
        aplication.setOther_Pets(request.isOther_Pets());
        aplication.setLocationDescription(request.getLocationDescription());
        aplication.setEnough_space(request.isEnough_space());
        aplication.setReason_adoption(request.getReason_adoption());
        aplication.setEnough_time(request.isEnough_time());

        aplication.setUsers(user);
        aplication.setPet(pet);

        aplication.setApplicationState(ApplicationState.PENDIENTE);

        aplication.setAplication_Date(new Date());

        aplicationRepository.save(aplication);
    }

    public void deleteAplication(UUID id){
        if (!aplicationRepository.existsById(id)) {
            throw new RuntimeException("Solicitud no encontrada");
        }
        aplicationRepository.deleteById(id);
    }

    public void updateApplicationState(UUID id, AplicationUpdateDto request) {
        Aplication application = aplicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada"));

        application.setApplicationState(ApplicationState.valueOf(request.getAplicationStatus()));
        aplicationRepository.save(application);
    }
}
