package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Ubication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UbicationRepository extends JpaRepository<Ubication, Integer> {
}
