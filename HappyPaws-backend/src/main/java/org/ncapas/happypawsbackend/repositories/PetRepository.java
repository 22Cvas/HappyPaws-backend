package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Pet;
import org.ncapas.happypawsbackend.Domain.Enums.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    //estado
    List<Pet> findByStatus(PetStatus status);

    // creador
    List<Pet> findByUserId(UUID userId);

    // vacunados
    List<Pet> findByFullyVaccinatedTrue();

    // esterilizados
    List<Pet> findBySterilizedTrue();

    // desparasitados
    List<Pet> findByParasiteFreeTrue();

}
