package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
}
