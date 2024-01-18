package com.mrizkyff.contactmanager.repository;

import com.mrizkyff.contactmanager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {

}
