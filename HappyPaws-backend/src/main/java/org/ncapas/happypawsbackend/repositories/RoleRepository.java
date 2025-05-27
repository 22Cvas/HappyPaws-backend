package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Rol, Integer> {

    //List<Role> findRoleByName(String name);
}
