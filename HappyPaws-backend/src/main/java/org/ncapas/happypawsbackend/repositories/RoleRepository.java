package org.ncapas.happypawsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findRoleByName(String name);
}
