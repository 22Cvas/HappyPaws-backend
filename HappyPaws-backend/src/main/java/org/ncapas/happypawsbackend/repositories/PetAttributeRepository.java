package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Pet_Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetAttributeRepository extends JpaRepository<Pet_Attribute, Integer> {
}
