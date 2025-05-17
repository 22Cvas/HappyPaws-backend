package org.ncapas.happypawsbackend.repositories;

import org.ncapas.happypawsbackend.Domain.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
