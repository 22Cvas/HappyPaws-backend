package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Aplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AplicationRepository extends JpaRepository<Aplication, Integer> {


    List<Aplication> findAplicationByAplication_Date(LocalDate aplication_date);
    List<Aplication> findAplicationByCompletion_Date(LocalDate completion_date);
}
