package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShelterRepository extends JpaRepository<Shelter, Integer> {
}
