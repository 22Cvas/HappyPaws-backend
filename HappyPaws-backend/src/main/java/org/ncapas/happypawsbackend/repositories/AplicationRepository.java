package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Aplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AplicationRepository extends JpaRepository<Aplication, Integer> {


    List<Aplication> findAplicationByAplication_Date(LocalDate aplication_date);
    List<Aplication> findAplicationByCompletion_Date(LocalDate completion_date);
}
