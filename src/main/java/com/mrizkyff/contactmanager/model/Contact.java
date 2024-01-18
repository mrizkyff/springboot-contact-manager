package com.mrizkyff.contactmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@Entity
@Table (name = "contacts") // 'contacts' is the name of the table in the database 'contact_manager
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    private String email;

    private String phone;

    private String username;

    private String password;

}
