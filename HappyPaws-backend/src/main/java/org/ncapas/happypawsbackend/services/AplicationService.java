package org.ncapas.happypawsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.ncapas.happypawsbackend.Domain.Entities.Aplication;
import org.ncapas.happypawsbackend.Domain.Entities.Pet;
import org.ncapas.happypawsbackend.Domain.Entities.User;
import org.ncapas.happypawsbackend.Domain.Enums.ApplicationState;
import org.ncapas.happypawsbackend.Domain.dtos.AplicationRegisterDto;
import org.ncapas.happypawsbackend.Domain.dtos.AplicationResponse;
import org.ncapas.happypawsbackend.Domain.dtos.AplicationUpdateDto;
import org.ncapas.happypawsbackend.Domain.dtos.AplicationUserDto;
import org.ncapas.happypawsbackend.repositories.AplicationRepository;
import org.ncapas.happypawsbackend.repositories.PetRepository;
import org.ncapas.happypawsbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class AplicationService {

    @Autowired
    private AplicationRepository aplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    public void createAplication(AplicationRegisterDto request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

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

    public void deleteAplication(UUID id) {
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

    public List<AplicationResponse> getAllApplications() {
        List<Aplication> applications = aplicationRepository.findAll();
        List<AplicationResponse> dtos = new ArrayList<>();

        for (Aplication ap : applications) {
            AplicationResponse dto = new AplicationResponse();
            dto.setId(ap.getId_aplication());
            dto.setAplicationDate(ap.getAplication_Date());
            dto.setOtherPets(ap.isOther_Pets());
            dto.setReasonAdoption(ap.getReason_adoption());
            dto.setEnoughSpace(ap.isEnough_space());
            dto.setEnoughTime(ap.isEnough_time());
            dto.setLocationDescription(ap.getLocationDescription());
            dto.setAplicationState(String.valueOf(ap.getApplicationState()));

            if (ap.getPet() != null) {
                dto.setPetId(ap.getPet().getId());
            }

            dtos.add(dto);
        }
        return dtos;
    }

    public AplicationResponse getAplicationById(UUID id) {
        Optional<Aplication> opt = aplicationRepository.findById(id);
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("Aplicación no encontrada con id: " + id);
        }
        Aplication a = opt.get();

        AplicationResponse dto = new AplicationResponse();
        dto.setId(a.getId_aplication());
        dto.setAplicationDate(a.getAplication_Date());
        dto.setOtherPets(a.isOther_Pets());
        dto.setReasonAdoption(a.getReason_adoption());
        dto.setEnoughSpace(a.isEnough_space());
        dto.setEnoughTime(a.isEnough_time());
        dto.setLocationDescription(a.getLocationDescription());
        dto.setAplicationState(String.valueOf(a.getApplicationState()));

        if (a.getPet() != null) {
            dto.setPetId(a.getPet().getId());
        }

        return dto;
    }

    public List<AplicationUserDto> getAplicationsByUser(String email) {
        List<Aplication> solicitudes = aplicationRepository.findByUsersEmail(email);

        return solicitudes.stream().map(ap -> {
            Pet pet = ap.getPet();

            return new AplicationUserDto(
                    pet.getName(),
                    ap.getAplication_Date()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate(),
                    ap.getApplicationState(),
                    pet.getGender().name(),
                    pet.getSpecies().getName()
            );
        }).collect(Collectors.toList());
    }

    public String getLoggedUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public List<AplicationUserDto> getAcceptedAplicationsByLoggedUser() {
        // Obtener el email del usuario autenticado desde el contexto de seguridad
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Buscar las solicitudes con estado ACEPTED para ese usuario
        List<Aplication> solicitudes = aplicationRepository
                .findByUsersEmailAndApplicationState(email, ApplicationState.ACEPTADA);

        // Convertir las solicitudes a DTO
        return solicitudes.stream().map(ap -> {
            Pet pet = ap.getPet();

            return new AplicationUserDto(
                    pet.getName(),
                    ap.getAplication_Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    ap.getApplicationState(),
                    pet.getGender().name(),
                    pet.getSpecies().getName()
            );
        }).collect(Collectors.toList());
    }


}





