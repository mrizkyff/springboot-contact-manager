package com.mrizkyff.contactmanager.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.contactmanager.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ContactServiceImplTest {

    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createContactSuccess() throws JsonProcessingException {
        Contact contact = Contact.builder()
                .name("Ian Febrian")
                .email("ian@mail.com")
                .phone("081231233")
                .username("ianfebrian")
                .password("admin")
                .build();

        Contact savedContact = contactService.createContact(contact);
        log.info("savedContact : " + objectMapper.writeValueAsString(savedContact));
        assertNotNull(savedContact);
        assertNotNull(savedContact.getId());
        assertEquals(contact.getName(), savedContact.getName());
        assertEquals(contact.getEmail(), savedContact.getEmail());
        assertEquals(contact.getPhone(), savedContact.getPhone());
        assertEquals(contact.getUsername(), savedContact.getUsername());
        assertEquals(contact.getPassword(), savedContact.getPassword());
    }

    @Test
    void getContactById() {
    }

    @Test
    void getAllContacts() {
    }
}