package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Ubication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UbicationRepository extends JpaRepository<Ubication, Integer> {
}
