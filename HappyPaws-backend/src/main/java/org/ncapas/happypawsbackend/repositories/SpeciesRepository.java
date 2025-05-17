package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {
}
