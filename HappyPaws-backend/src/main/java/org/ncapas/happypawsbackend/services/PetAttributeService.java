package org.ncapas.happypawsbackend.services;

import lombok.RequiredArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.Pet;
import org.ncapas.happypawsbackend.Domain.Entities.Pet_Attribute;
import org.ncapas.happypawsbackend.Domain.dtos.PetAttributeRequestDto;
import org.ncapas.happypawsbackend.Domain.dtos.PetAttributeResponseDto;
import org.ncapas.happypawsbackend.repositories.PetAttributeRepository;
import org.ncapas.happypawsbackend.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetAttributeService {

    private final PetRepository petRepository;
    private final PetAttributeRepository attributeRepository;

    public void create(PetAttributeRequestDto dto) {
        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Pet_Attribute attr = new Pet_Attribute();
        attr.setAttributeName(dto.getAttributeName());
        attr.setAttributeValue(dto.getAttributeValue());
        attr.setPet(pet);

        attributeRepository.save(attr);
    }

    public List<PetAttributeResponseDto> getByPet(UUID petId) {
        return attributeRepository.findByPet_Id(petId)
                .stream()
                .map(attr -> PetAttributeResponseDto.builder()
                        .id(attr.getId_pet_attribute())
                        .attributeName(attr.getAttributeName())
                        .attributeValue(attr.getAttributeValue())
                        .petName(attr.getPet().getName())
                        .build())
                .collect(Collectors.toList());
    }

    public void update(Integer id, PetAttributeRequestDto dto) {
        Pet_Attribute attr = attributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atributo no encontrado"));

        attr.setAttributeName(dto.getAttributeName());
        attr.setAttributeValue(dto.getAttributeValue());

        attributeRepository.save(attr);
    }

    public void delete(Integer id) {
        if (!attributeRepository.existsById(id)) {
            throw new RuntimeException("Atributo no encontrado");
        }
        attributeRepository.deleteById(id);
    }
}
