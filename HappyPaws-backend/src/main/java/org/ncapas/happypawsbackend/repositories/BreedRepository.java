package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Integer> {


    List<Breed> findBreedByName(String name);
}
