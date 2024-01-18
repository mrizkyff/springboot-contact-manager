package com.mrizkyff.contactmanager.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.contactmanager.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@Slf4j
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveContactSuccess() throws JsonProcessingException {
        //
        List<Contact> initialContacts = contactRepository.findAll();
        assertEquals(0 , initialContacts.size());

        Contact contact = Contact.builder()
                .name("Ian Febrian")
                .email("ian@mail.com")
                .phone("081231233")
                .username("ianfebrian")
                .password("admin")
                .build();

        contactRepository.save(contact);

        List<Contact> contacts = contactRepository.findAll();
        log.info("contacts : " + objectMapper.writeValueAsString(contacts));
        assertEquals(1, contacts.size());
        assertEquals(contact.getName(), contacts.getFirst().getName());
        assertEquals(contact.getEmail(), contacts.getFirst().getEmail());
        assertEquals(contact.getPhone(), contacts.getFirst().getPhone());
        assertEquals(contact.getUsername(), contacts.getFirst().getUsername());
        assertEquals(contact.getPassword(), contacts.getFirst().getPassword());
    }

    @Test
    void saveContactFail() {
        Contact contact = Contact.builder()
                .name("Ian Febrian")
                .email("ian@mail.com")
                .phone("081231233")
                .username("ianfebrian")
                .password("admin")
                .build();

        assertThrows(Exception.class, () -> {
            contactRepository.save(contact);
        });
    }
}