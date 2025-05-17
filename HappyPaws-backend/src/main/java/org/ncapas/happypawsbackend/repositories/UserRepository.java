package org.ncapas.happypawsbackend.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.ncapas.happypawsbackend.Domain.Entities.Rol;
import org.ncapas.happypawsbackend.Domain.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findById_Users(Integer id_users);

    List<Users> findByRoles(List<Rol> roles);

    List<Users> findAllByRolesIs(List<Rol> roles);

    List<Users> findByRoleNameAndState(String role, boolean state);


}
