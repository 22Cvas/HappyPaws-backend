package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Rol, Integer> {
    @Query("SELECT r FROM Rol r WHERE UPPER(r.name) = UPPER(:name)")
    Optional<Rol> findRolByName(@Param("name") String name);


}
