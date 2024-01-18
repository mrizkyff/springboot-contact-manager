package com.mrizkyff.contactmanager.service.impl;

import com.mrizkyff.contactmanager.dto.contact.ContactRequestDto;
import com.mrizkyff.contactmanager.dto.contact.ContactResponseDto;
import com.mrizkyff.contactmanager.exception.DataNotFoundException;
import com.mrizkyff.contactmanager.model.Contact;
import com.mrizkyff.contactmanager.repository.ContactRepository;
import com.mrizkyff.contactmanager.security.BCrypt;
import com.mrizkyff.contactmanager.service.ContactService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact createContact(Contact contact) {
        contact.setPassword(BCrypt.hashpw(contact.getPassword(), BCrypt.gensalt()));
        contactRepository.save(contact);
        return getContactById(contact.getId(), false);
    }

    @Override
    public Contact getContactById(UUID id, boolean nullable) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (!nullable){
            if (contact == null) {
                throw new DataNotFoundException("Contact not found");
            }
        }
        return contact;
    }

    @Override
    public Page<Contact> getAllContacts(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    public Contact updateContact(Contact contact , UUID id) {
        Contact existingContact = getContactById(id , false);
        if (contact.getName() != null) {
            existingContact.setName(contact.getName());
        }
        if (contact.getEmail() != null) {
            existingContact.setEmail(contact.getEmail());
        }
        if (contact.getPhone() != null) {
            existingContact.setPhone(contact.getPhone());
        }
        if (contact.getUsername() != null) {
            existingContact.setUsername(contact.getUsername());
        }
        if (contact.getPassword() != null) {
            existingContact.setPassword(contact.getPassword());
        }

        contactRepository.save(existingContact);
        return getContactById(id , false);
    }

    @Override
    public void deleteContact(UUID id) {
        Contact contact = getContactById(id , false);
        contactRepository.delete(contact);
    }

    @Override
    public ContactResponseDto mapToDto(Contact contact) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(contact, ContactResponseDto.class);
    }

    @Override
    public Contact mapToEntity(ContactRequestDto contactRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(contactRequestDto, Contact.class);
    }
}
