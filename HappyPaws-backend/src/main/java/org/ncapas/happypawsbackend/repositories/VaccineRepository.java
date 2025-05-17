package org.ncapas.happypawsbackend.repositories;
import org.ncapas.happypawsbackend.Domain.Entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {


    Vaccine findVaccineByName(String name);


}
