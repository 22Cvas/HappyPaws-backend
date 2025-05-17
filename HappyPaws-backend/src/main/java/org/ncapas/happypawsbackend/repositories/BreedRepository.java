package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Integer> {


    List<Breed> findBreedByName(String name);
}
