package com.mrizkyff.contactmanager.service;

import com.mrizkyff.contactmanager.dto.contact.ContactRequestDto;
import com.mrizkyff.contactmanager.dto.contact.ContactResponseDto;
import com.mrizkyff.contactmanager.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ContactService {
    Contact createContact(Contact contact);

    Contact getContactById(UUID id, boolean nullable);

    Page<Contact> getAllContacts(Pageable pageable);

    Contact updateContact(Contact contact, UUID id);

    void deleteContact(UUID id);

    ContactResponseDto mapToDto(Contact contact);

    Contact mapToEntity(ContactRequestDto contactRequestDto);
}
